package com.jh.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jh.bean.Account;
import com.jh.bean.Message;
import com.jh.bean.QQSocket;
import com.jh.common.Constants;
import com.jh.dao.AccountDAO;
import com.jh.dao.AccountDAOImpl;

/**
 * QQ服务端，负责连接所有的Socket，并把所有Socket保持
 * 消息的接收和消息的转发（消息的传递）
 * @author Administrator
 *
 */
public class Server {
	
	private List<QQSocket> sockets;

	public Server() {
		sockets = new ArrayList<QQSocket>();
		new Thread(new Connector()).start(); // 开始连接线程
	}
	
	/**
	 * 通过线程来建立连接，此建立过程是用不停止的，一直需要客户端的QQ用户登入，
	 * 一旦有用户登入，则把此Socket和QQ用户关联，再保存到服务端的sockets列表中
	 * 
	 * 开启此连接的读取线程，去读取此QQ用户发来的消息
	 * @author Administrator
	 *
	 */
	class Connector implements Runnable {
		@Override
		public void run() {
			try {
				@SuppressWarnings("resource") // 压制警告
				ServerSocket ss = new ServerSocket(Constants.PORT);
				while (true) {
					System.out.println("服务器正在等待连接");
					Socket socket = ss.accept(); // 等待连接
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); // 读进一个对象
					Object obj = ois.readObject();
					if (obj instanceof Account) { // 判断读进来的对象是否是Account的对象
						Account account = (Account) obj; // 强制把该对象转换成Account对象
						System.out.println(account.getNumber() + "已经登入");
						QQSocket qqSocket = new QQSocket(account, socket); // 给QQSocket设置好用户和Socket
						sockets.add(qqSocket); // 把qqSocket添加到集合中
						new Thread(new MessageReadThread(qqSocket)).start(); // 启动度消息线程
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 用此线程来读QQ用户发送过来的消息
	 * 只要建立了连接，客户端就有可能不定时的发送消息过来，所以服务端就必须一直去读
	 * 
	 * 一旦有消息被读取，则需要把此消息转发给在线的好友，先判断连接里是否有该好友，如果有，则使用ObjectOutputStream
	 * @author Administrator
	 *
	 */
	class MessageReadThread implements Runnable {
		
		private QQSocket qqSocket;
		
		public MessageReadThread(QQSocket qqSocket) {
			this.qqSocket = qqSocket;
		}
		
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Constants.MESSAGE_SLEEP); // 使线程休眠1s（1000ms）
					ObjectInputStream ois = new ObjectInputStream(qqSocket.getSocket().getInputStream()); 
					Object obj = ois.readObject(); // 去读传递进来的对象
					if (obj instanceof Message) { // 判断传递进来的obj对象是否是Message的对象
						Message message = (Message) obj;
						System.out.println("读取来自" + message.getFromAccount().getNumber() + "的消息");
						Socket toSocket = searchSocket(message.getToAccount()); // 获取到要发送消息的socket
						if (toSocket != null) { // 表示要发送消息的socket存在
							AccountDAO accountDAO = new AccountDAOImpl();
							if (message.getType() == Message.LOGOUT_MSG) { // 如果收到的消息是退出消息
								System.out.println(message.getFromAccount().getNumber() + "已退出");
								Account account = message.getFromAccount();
								account.setStatus("offline"); // 把用户的状态设置成离线状态
								accountDAO.update(account); // 更新数据库
								
								List<Account> accounts = accountDAO.queryNotOfflineFriends(account.getNumber());
								for (Account a : accounts) {
									Socket toSocket1 = searchSocket(a); // 获取对方账号所对应的Socket连接
									if (toSocket1 != null) {
										MessageWriteThread writer = new MessageWriteThread(toSocket1);
										writer.setMessage(message);
										new Thread(writer).start();
									}
								}
								removeAccountSocket(message.getFromAccount()); // remove掉退出的Socket
							} else if (message.getType() == Message.UPDATE_FRIEND_MSG) { // 如果收到的是更新好友列表的消息
								List<Account> accounts = accountDAO.queryNotOfflineFriends(message.getFromAccount().getNumber());
								for (Account a : accounts) {
									Socket toSocket1 = searchSocket(a); // 获取对方账号所对应的Socket连接
									if (toSocket1 != null) {
										MessageWriteThread writer = new MessageWriteThread(toSocket1);
										writer.setMessage(message);
										new Thread(writer).start();
									}
								}
							} else if (message.getType() == Message.UPDATE_DATA_MSG) { // 更新好友信息
								List<Account> accounts = accountDAO.queryNotOfflineFriends(message.getFromAccount().getNumber());
								for (Account a : accounts) {
									Socket toSocket1 = searchSocket(a); // 获取对方账号所对应的Socket连接
									if (toSocket1 != null) {
										MessageWriteThread writer = new MessageWriteThread(toSocket1);
										writer.setMessage(message);
										new Thread(writer).start();
									}
								}
							}  else {
								MessageWriteThread mwt = new MessageWriteThread(toSocket); // 告诉服务器接收消息的socket
								mwt.setMessage(message); // 把要转发的消息传递给线程
								new Thread(mwt).start(); // 执行发消息线程
							}
						}
					}
				} catch (SocketException e) {
					try {
						qqSocket.getSocket().close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 此线程用来转发用户消息
	 * 
	 * 如果有消息传进来，则执行此线程用来转发消息，转发完毕，线程死亡
	 * @author Administrator
	 *
	 */
	class MessageWriteThread implements Runnable {
		
		private Socket socket;
		private Message message; // 用来存放消息
		
		// 让服务器知道是给哪个用户发消息
		public MessageWriteThread(Socket socket) {
			this.socket = socket;
		}
		
		public Message getMessage() {
			return message;
		}
		
		public void setMessage(Message message) {
			this.message = message;
		}
		
		@Override
		public void run() {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); // 实例化输出流传递对象
				oos.writeObject(message); // 把存放消息的对象传递出去
				System.out.println("把消息发给了" + message.getToAccount().getNumber());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 判断要接收的账号是否已经连接此服务器
	 * @param account
	 * @return
	 */
	private Socket searchSocket(Account account) {
		for (QQSocket qqSocket : sockets) { // 去遍历sockets列表
			Account a = qqSocket.getAccount();
			if (a.equals(account)) {
				return qqSocket.getSocket();
			}
		}
		return null;
	}
	
	/**
	 * 用来移除Socket
	 * @param account
	 */
	public void removeAccountSocket(Account account) {
		Iterator<QQSocket> ite = sockets.iterator(); // 获取到sockets对应的迭代器
		while (ite.hasNext()) { // 判断迭代器里是否有值
			QQSocket qqSocket = ite.next(); // 获取到迭代器中的值
			if (qqSocket.getAccount().equals(account)) {
				ite.remove(); // 移除Socket
				break; // 结束循环
			}
		}
	}
	
}
