package com.xk.qq.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.xk.bean.Account;
import com.xk.bean.Message;
import com.xk.common.Constants;
import com.xk.common.DateUtil;
import com.xk.dao.AccountDAO;
import com.xk.dao.AccountDAOImpl;
import com.xk.qq.bean.QQSocket;

/**
 * QQ服务端，负责等待Socket连接，并把所有socket保持
 * 消息的接收与转发操作
 *
 */
public class Server {
	
	private List<QQSocket> sockets; 
	
	public Server() {
		sockets = new ArrayList<QQSocket>();
		new Thread(new Connect()).start();
	}
	
	/**
	 * 通过线程来建立连接，此建立的过程是永远不停止的，一直需要等待客户端QQ用户登录
	 * 一旦有客户端登录，则把此连接与QQ账号关联，组成QQSocket对象，再保存到服务端持有的所有连接信息的列表中（private List<QQSocket> sockets;）
	 * 
	 * 开户此连接的读线程，去读取此QQ账号对应的客户端发送过来的消息
	 *
	 */
	
	class Connect implements Runnable{
		
		@Override
		public void run() {
			try {
				ServerSocket serverSocket = new ServerSocket(1998);
				while(true){
					System.out.println("服务器正在等待客户端连接。。。。");
					Socket socket = serverSocket.accept(); // 不停的等连接
					ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
					Account account = (Account)oin.readObject(); // 读取登录用户
					System.out.println(account.getNumber() + "已登录");
					account.setState(Constants.STATUS_ONLINE);
					QQSocket qqSocket = new QQSocket(account,socket); // 读取用户和socket
					sockets.add(qqSocket);	// 再保存到服务端持有的所有连接信息放到集合里
					AccountDAO accountDAO = new AccountDAOImpl();
					accountDAO.updateStatus(account.getNumber(), Constants.STATUS_ONLINE);
					new Thread(new MessageReadThread(qqSocket)).start();// 去读取此QQ账号对应的客户端发送过来的消息
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
	class MessageReadThread implements Runnable{
		private QQSocket qqSocket;
		
		public MessageReadThread(QQSocket qqSocket){
			this.qqSocket = qqSocket;
		} 
		
		@Override
		public void run() {
			while(true){
//				synchronized (Object.class) {
				try {
					ObjectInputStream oin = new ObjectInputStream(qqSocket.getSocket().getInputStream());
					Message message = (Message)oin.readObject();	//读取消息
					Socket toSocket = searchSocket(message.getToAccount());	// 对方用户
					if(toSocket != null){ // 如果有创立连接 有这个用户
						if(message.getType() == Message.LOGOUT_MSG){
							System.out.println(message.getFromAccount().getNumber()+ "已退出");
							removeAccountSocket(message.getFromAccount());
							AccountDAO accountDAO = new AccountDAOImpl();
							message.getFromAccount().setState(Constants.STATUS_OFFLINE);
							List<Account> accounts = accountDAO.queryNotLeaveFriends(message.getFromAccount().getNumber());
							accountDAO.updateStatus(message.getFromAccount().getNumber(),message.getFromAccount().getState());
							for(Account a: accounts){
								Socket toSocket1 = searchSocket(a);	// 获取对方用户的socket连接
								if(toSocket1 != null){
									MessageWriteThread write = new MessageWriteThread(toSocket1);//那么发消息给对方
									write.setMessage(message);	// 告诉他消息
									new Thread(write).start();	// 启动线程
									accountDAO.updateStatus(message.getFromAccount().getNumber(), message.getFromAccount().getState());
								}
							}
						}else if(message.getType() == Message.STATES_CHANGE){
							AccountDAO accountDAO = new AccountDAOImpl();
							List<Account> accounts = accountDAO.queryNotLeaveFriends(message.getFromAccount().getNumber());
							accountDAO.updateStatus(message.getFromAccount().getNumber(), message.getFromAccount().getState());
							for(Account a: accounts){
								Socket toSocket1 = searchSocket(a);	// 获取对方用户的socket连接
								if(toSocket1 != null){
									MessageWriteThread write = new MessageWriteThread(toSocket1);//那么发消息给对方
									write.setMessage(message);	// 告诉他消息
									new Thread(write).start();	// 启动线程
									accountDAO.updateStatus(message.getFromAccount().getNumber(), message.getFromAccount().getState());
								}
							}
								
							
						}else {
							MessageWriteThread write = new MessageWriteThread(toSocket);//那么发消息给对方
							write.setMessage(message);	// 告诉他消息
							new Thread(write).start();	// 启动线程
						}
					}
				}catch(SocketException e){	// 如果捕获到socket异常
					try {
						qqSocket.getSocket().close(); // 那么关掉
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
//			}
			}
		}
		
	}
	
	/**
	 * 服务端向客户端转发消息的线程
	 * 
	 * 如果客户端有消息进来，则需要开户此线程做转发操作，转发完毕，此线程死亡
	 *
	 */
	
	class MessageWriteThread implements Runnable{
		private Socket socket;
		private Message message;
		public Message getMessage() {
			return message;
		}

		public void setMessage(Message message) {
			this.message = message;
		}

		public MessageWriteThread(Socket socket){
			this.socket = socket;
		}
		
		@Override
		public void run() {
			try {
				ObjectOutputStream oout = new ObjectOutputStream(socket.getOutputStream());
				oout.writeObject(message); // 如果客户端有消息进来，则需要开户此线程做转发操作，转发完毕，此线程死亡
			} catch(SocketException e){	// 如果捕获到socket异常
				try {
					socket.close(); // 那么关掉
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 判断某个QQ账号是否已经与服务器建立 了连接
	 * @param account
	 * @return
	 */
	
	private Socket searchSocket(Account account){
		for(QQSocket qqSocket: sockets){
			Account accounts = qqSocket.getAccount();
			if(accounts.equals(account)){ // 判断某个账号是否登录
				return qqSocket.getSocket();
			}
			
		}
		return null;
	}
	
	private void removeAccountSocket(Account account) {
		Iterator<QQSocket> iter = sockets.iterator();
		while(iter.hasNext()){
			QQSocket qqSocket = iter.next();
			if(qqSocket.getAccount().equals(account)){
				iter.remove();
				break;
			}
		}
	}
}
