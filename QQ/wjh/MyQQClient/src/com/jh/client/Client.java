package com.jh.client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.jh.bean.Account;
import com.jh.bean.Message;
import com.jh.common.Constants;
import com.jh.dao.AccountDAO;
import com.jh.dao.AccountDAOImpl;
import com.jh.ui.ChatFrame;
import com.jh.ui.MainFrame;

/**
 * QQ客户端，用来连接服务器，
 * 负责消息的接收和发送
 * 
 * @author Administrator
 *
 */
public class Client {

	private Socket socket;
	private MainFrame mainFrame;
	private ChatFrame chatFrame;
	
	public Client() {}
	
	/**
	 * 设置窗体，把主窗体传递进来
	 * @param frame
	 */
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	/**
	 * 用来登入QQ号，并且把QQ发送到服务器
	 * 如果此QQ号存在，则建立连接
	 * 
	 * 立马去开启读消息线程，检测服务器是否有发送消息过来
	 * @param account
	 */
	public void login(Account account) {
		try {
			socket = new Socket("localhost", Constants.PORT); // 去连接服务器
			if (socket != null) {
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); // 实例化输出流
				oos.writeObject(account); // 把存放QQ号的对象传递给服务器
				new Thread(new MessageReadThread(socket)).start();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用来发送送消息， 并开启发消息线程
	 * @param message
	 */
	public void sendMessage(Message message) {
		MessageWriteThread mwt = new MessageWriteThread(socket);
		mwt.setMessage(message);
		new Thread(mwt).start(); // 开启写消息线程
	}

	/**
	 * 此线程用来读取服务器发来的消息，需要一直开启
	 * @author Administrator
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
					Thread.sleep(Constants.MESSAGE_SLEEP);
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					Object obj = ois.readObject();
					if (obj instanceof Message) {
						Message message = (Message) obj;
						if (message != null) {
							chatFrame = mainFrame.getChatFrames().get(message.getFromAccount());
							if (message.getType() == Message.NORMAL_MSG) {
								System.out.println("接收来自" + message.getFromAccount().getNumber() + "的消息：");
								System.out.println("消息内容：" + message.getMessage());
								if (chatFrame == null || !chatFrame.isVisible()) { // 如果聊天窗口是不可见的或者没有打开聊天窗口
									mainFrame.requstMessage(message); // 启动消息闪动的线程
								} else {
									chatFrame.updateMessage(message); // 更新消息
								}
							} else if (message.getType() == Message.REQUST_MSG) {
								mainFrame.requstAddFriend(message); // 启动消息闪动线程
							} else if (message.getType() == Message.REQUST_REV_MSG) {
								Account account = message.getFromAccount(); // 获取到发消息的对象
								AccountDAO accountDAO = new AccountDAOImpl();
								accountDAO.addFriends(message.getToAccount().getNumber(), message.getFromAccount().getNumber()); // 将数据添加到数据库
								mainFrame.updateFriendList(account);
							} else if (message.getType() == Message.LOGOUT_MSG) {
								Account account = message.getFromAccount();
								mainFrame.updateFriendData(account);
							} else if (message.getType() == Message.UPDATE_FRIEND_MSG) {
								Account account = message.getFromAccount();
								mainFrame.updateFriendData(account);
							} else if (message.getType() == Message.UPDATE_DATA_MSG) {
								Account account = message.getFromAccount();
								mainFrame.updateFriendData(account);
							} else if (message.getType() == Message.SELECT_PWD_MSG) {
								mainFrame.selectPwdPrompt();
							} else if (message.getType() == Message.SEND_FILE_MSG) {
								try {
									String fileName = message.getMessage();
									byte[] bytes = message.getBytes();
									FileOutputStream fos = new FileOutputStream("E:/workspace/MyQQClient/src/images/img/" + fileName);
									fos.write(bytes);
									fos.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
								if (chatFrame == null || !chatFrame.isVisible()) { // 如果聊天窗口是不可见的或者没有打开聊天窗口
									mainFrame.requstMessage(message); // 启动消息闪动的线程
								} else {
									chatFrame.updateMessage(message); // 更新消息
								}
							} else if (message.getType() == Message.SHAKE_MSG) { // 抖一抖
								if (chatFrame != null) {
									chatFrame.updateMessage(message);
									chatFrame.updateShake();
								}
							}
						}
					}
				} catch (SocketException e) {
					try {
						socket.close();
						break; // 结束循环
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
	 * 写消息线程，把消息发送给服务器，完毕后此线程死亡
	 * @author Administrator
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
				System.out.println("发送消息给" + message.getToAccount().getNumber());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
