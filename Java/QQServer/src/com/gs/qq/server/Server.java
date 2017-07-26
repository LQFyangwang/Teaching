package com.gs.qq.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gs.bean.Account;
import com.gs.bean.Message;
import com.gs.common.Constants;
import com.gs.dao.AccountDAO;
import com.gs.dao.AccountDAOImpl;
import com.gs.qq.bean.QQSocket;

/**
 * QQ服务端，负责等待Socket连接，并把所有socket保持
 * 消息的接收与转发操作
 *
 */
public class Server {
	
	private List<QQSocket> sockets;
	
	public Server() {
		sockets = new ArrayList<QQSocket>(); 
		new Thread(new Connector()).start();
	}
	
	/**
	 * 通过线程来建立连接，此建立的过程是永远不停止的，一直需要等待客户端QQ用户登录
	 * 一旦有客户端登录，则把此连接与QQ账号关联，组成QQSocket对象，再保存到服务端持有的所有连接信息的列表中（private List<QQSocket> sockets;）
	 * 
	 * 开户此连接的读线程，去读取此QQ账号对应的客户端发送过来的消息
	 *
	 */
	class Connector implements Runnable {

		@Override
		public void run() {
			try {
				ServerSocket serverSocket = new ServerSocket(Constants.PORT);
				while (true) {
					System.out.println("服务器正在等待客户端的连接");
					Socket socket = serverSocket.accept();
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					Account account = (Account) ois.readObject(); // 读取登录账号
					System.out.println(account.getNumber() + "已登录");
					account.setStatus(Constants.STATUS_ONLINE);
					QQSocket qqSocket = new QQSocket(account, socket);
					sockets.add(qqSocket);
					AccountDAO accountDAO = new AccountDAOImpl();
					accountDAO.updateStatus(account.getNumber(), Constants.STATUS_ONLINE);
					new Thread(new MessageReadThread(qqSocket)).start();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 服务端读取客户端消息线程
	 * 一旦客户端建立了连接，则客户端在不定时就有消息发送过来，所以服务端必须不停地去读取是否有客户端消息
	 * 
	 * 读取Message对象，所有使用ObjectInputStream
	 * 
	 * 一旦有消息被读取，则意味着要把此消息转发到好友，转发到对方好友前必须先判断已经建立的连接里是否有该好友，如果有，则使用ObjectOutputStream
	 * 把此消息转发（开户写线程把消息发送出去）
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
					Thread.sleep(Constants.MESSAGE_DELAY);
					ObjectInputStream oin = new ObjectInputStream(qqSocket.getSocket().getInputStream());
					Message message = (Message) oin.readObject();
					System.out.println("读取来自于" + message.getFromAccount().getNumber() + "的消息");
					Socket toSocket = searchSocket(message.getToAccount()); // 获取对方账号所对应的Socket连接
					if (toSocket != null) {
						if (message.getType() == Message.LOGOUT_MSG) {
							System.out.println(message.getFromAccount().getNumber() + "已退出");
							removeAccountScoket(message.getFromAccount());
							AccountDAO accountDAO = new AccountDAOImpl();
							accountDAO.updateStatus(message.getFromAccount().getNumber(), Constants.STATUS_OFFLINE);
						} else if (message.getType() == Message.STATUS_CHANGE_MSG) {
							AccountDAO accountDAO = new AccountDAOImpl();
							List<Account> accounts = accountDAO.queryNotLeaveFriends(message.getFromAccount().getNumber());
							for (Account a : accounts) {
								Socket toSocket1 = searchSocket(a); // 获取对方账号所对应的Socket连接
								if (toSocket1 != null) {
									MessageWriteThread writer = new MessageWriteThread(toSocket1);
									writer.setMessage(message);
									new Thread(writer).start();
								}
							}
						} else {
							MessageWriteThread writer = new MessageWriteThread(toSocket);
							writer.setMessage(message);
							new Thread(writer).start();
						}
					}
				} catch (SocketException e) {
					try {
						qqSocket.getSocket().close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					sockets.remove(qqSocket);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * 服务端向客户端转发消息的线程
	 * 
	 * 如果客户端有消息进来，则需要开户此线程做转发操作，转发完毕，此线程死亡
	 *
	 */
	class MessageWriteThread implements Runnable {
		private Socket socket;
		private Message message;
		
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
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(message);
				System.out.println("把消息发送到" + message.getToAccount().getNumber());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 判断某个QQ账号是否已经与服务器建立 了连接
	 * @param account
	 * @return
	 */
	private Socket searchSocket(Account account) {
		for (QQSocket qqSocket : sockets) {
			Account a = qqSocket.getAccount();
			if (a.equals(account)) {
				return qqSocket.getSocket();
			}
		}
		return null;
	}
	
	private void removeAccountScoket(Account account) {
		Iterator<QQSocket> ite = sockets.iterator();
		while (ite.hasNext()) {
			QQSocket qqSocket = ite.next();
			if (qqSocket.getAccount().equals(account)) {
				ite.remove();
				break;
			}
		}
	}

	
}
