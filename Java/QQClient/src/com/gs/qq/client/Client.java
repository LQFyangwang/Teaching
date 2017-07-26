package com.gs.qq.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import com.gs.bean.Account;
import com.gs.bean.Message;
import com.gs.common.Constants;
import com.gs.dao.AccountDAO;
import com.gs.dao.AccountDAOImpl;
import com.gs.qq.ui.MainFrame;

/**
 * QQ客户端，负责连接服务器
 * 消息的接收与发送
 * @param <QQMessageMusic>
 *
 */
public class Client<QQMessageMusic> {
	
	private Socket socket;
	private MainFrame mainFrame;
	
	public Client() {
		
	}
	
	/**
	 * 把主窗口设置给交互类
	 * @param mainFrame
	 */
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	/**
	 * 客户端与服务端建立连接 ，并登录QQ账号
	 * 需要把此QQ账号发送到服务端，如果服务端存在此QQ账号，则建立连接
	 * 
	 * 立马开户客户端的读线程，不停地去读取是否有来自于服务端的消息
	 * @param account
	 */
	public void login(Account account) {
		try {
			socket = new Socket("localhost", Constants.PORT);
			if (socket == null) {
				System.out.println("aaaa");
			}
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(account);
			new Thread(new MessageReadThread(socket)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 向QQ好友发送消息
	 * 开启写线程进行消息发送
	 * @param message
	 */
	public void sendMessage(Message message) {
		MessageWriteThread writer = new MessageWriteThread(socket);
		writer.setMessage(message);
		new Thread(writer).start();
	}
	
	/**
	 * 不停地从服务端读取消息，如果读取到 了消息，则意味着有好友发送消息到自己
	 *
	 */
	class MessageReadThread implements Runnable {

		private Socket socket;
		
		public MessageReadThread(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Constants.MESSAGE_DELAY);
					ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
					Message message = (Message) oin.readObject();
					if (message != null) {
						if (message.getType() == Message.NORMAL_MSG) {
							// TODO 如果说聊天界面未打开，同主界面的消息图标闪动。如果聊天界面已经打开，则直接显示在聊天界面
							System.out.println("接收来自" + message.getFromAccount().getNumber() + "的消息");
							System.out.println( message.getMessage());
							mainFrame.getChatFrames().get(message.getFromAccount()).updateMessage(message);
						} else if (message.getType() == Message.REQUEST_MSG) {
							mainFrame.receiveRequestMsg(message); // 则主界面图标提示
							
						} else if (message.getType() == Message.REQUEST_REV_MSG) {
							Account account = message.getFromAccount();
							AccountDAO accountDAO = new AccountDAOImpl();
							accountDAO.addFriend(message.getToAccount().getNumber(), message.getFromAccount().getNumber());
							mainFrame.updateFriendList(account); // 更新好友列表
						} else if (message.getType() == Message.STATUS_CHANGE_MSG) {
							Account account = message.getFromAccount();
							mainFrame.updateFriendStatus(account);
						}
					}
				} catch (SocketException e) {
					try {
						socket.close();
						break;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
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
	 * 写消息线程
	 * 一旦把消息发送到了服务端，则此线程死亡
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
				System.out.println("发送消息给" + message.getToAccount().getNumber());
			} catch (SocketException e) {
				if (!socket.isClosed()) {
					try {
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
