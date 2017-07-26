package com.xk.qq.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import com.xk.bean.Account;
import com.xk.bean.Message;
import com.xk.dao.AccountDAO;
import com.xk.dao.AccountDAOImpl;
import com.xk.qq.ui.ChatFrame;
import com.xk.qq.ui.MainFrame;

public class Client {
	private Socket socket;
	private MainFrame mainFrame;
	
	
	
	/**
	 * 把主窗口设置给交互类
	 * @param mainFrame
	 */
	public void setMainframe(MainFrame mainFrame){
		this.mainFrame = mainFrame;
	}
	
	
	/**
	 * 客户端与服务端建立连接 ，并登录QQ账号
	 * 需要把此QQ账号发送到服务端，如果服务端存在此QQ账号，则建立连接
	 * 
	 * 立马开户客户端的读线程，不停地去读取是否有来自于服务端的消息
	 * @param account
	 */
	public void login(Account account){
		try {
			socket = new Socket("localhost",1998);	//连接服务端
			ObjectOutputStream oout = new ObjectOutputStream(socket.getOutputStream()); // 读取QQ号
			oout.writeObject(account);	
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
	public void sendMessage(Message message){
		new Thread(new MessageWriteThread(socket,message)).start();
	}
	
	/**
	 * 不停地从服务端读取消息，如果读取到 了消息，则意味着有好友发送消息到自己
	 *
	 */
	class MessageReadThread implements Runnable {
		private Socket socket;
		public MessageReadThread(Socket socket){
			this.socket = socket;
		}
		
		@Override
		public void run() {
			while(true){
					
				try {
					ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
					Message message = (Message)oin.readObject();	// 不停从服务端读消息
					if(message != null){	// 如果有消息，则发消息给好友
						if(message.getType() == Message.NORMAL_MSG){
							// TODO 如果说聊天界面未打开，同主界面的消息图标闪动。如果聊天界面已经打开，则直接显示在聊天界面
							System.out.println("来自：" + message.getFromAccount().getNumber() + "的消息");
							System.out.println(message.getMessage());
							System.out.println("更新对方的窗口:" + message.getFromAccount().getNumber());
							mainFrame.getChatFrames().get(message.getFromAccount()).updateMessage(message);
						}else if(message.getType() == Message.REQUEST_MSG){
							// TODO 主界面的喇叭闪动， 更新主界面
							mainFrame.receiveRepThread(message);
						}else if(message.getType() == Message.REQUEST_REV_MSG){
							Account account = message.getFromAccount();
							AccountDAO accountDAO = new AccountDAOImpl();
							// 接收对方消息，同时也把对方加到好友列表中
							accountDAO.addFriend(message.getToAccount().getNumber(), message.getFromAccount().getNumber());
							mainFrame.updateFriendList(account); // 更新好友列表
							
							accountDAO.deleteBalck(message.getToAccount().getNumber(), message.getFromAccount().getNumber());
							mainFrame.deleteBlckList(account);
						}else if(message.getType() == Message.STATES_CHANGE){
							Account account = message.getFromAccount();
							mainFrame.updateFriendStates(account);
						}else if(message.getType() == Message.DELETE_MSG){
							Account account = message.getFromAccount();
							AccountDAO accountDAO = new AccountDAOImpl();
							accountDAO.deleteFriend(message.getToAccount().getNumber(),message.getFromAccount().getNumber());
							mainFrame.deleteFriend(account);
						}else if(message.getType() == Message.LOGOUT_MSG){
							Account account = message.getFromAccount();
							mainFrame.updateFriendStates(account);
						}else if(message.getType() == Message.BLACKLIST_MSG){
							Account account = message.getFromAccount();
							AccountDAO accountDAO = new AccountDAOImpl();
							accountDAO.deleteFriend(message.getToAccount().getNumber(),message.getFromAccount().getNumber());
							mainFrame.deleteFriend(account);
							
							accountDAO.addBlack(message.getToAccount().getNumber(), message.getFromAccount().getNumber());
							mainFrame.updateBlackList(account);
							
						}else if(message.getType() == Message.SHAKE_MSG){
							mainFrame.getChatFrames().get(message.getFromAccount()).updateShake();
				
						}
						
					}
				} catch(SocketException e){
					try {
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
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
		public MessageWriteThread(Socket socket, Message message) {
			this.socket = socket;
			this.message = message;
		}
		@Override
		public void run() {
			try {
				ObjectOutputStream oout = new ObjectOutputStream(socket.getOutputStream());
				oout.writeObject(message);
				
			} catch(SocketException e){
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
