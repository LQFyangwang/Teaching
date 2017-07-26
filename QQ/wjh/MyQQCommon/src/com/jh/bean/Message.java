package com.jh.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * QQ消息类，用来保存发送人，接收人，发送时间和发送的消息
 * 
 * @author Administrator
 *
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 1818829536440641381L;

	public static final int NORMAL_MSG = 1; // 正常的消息
	public static final int REQUST_MSG = 2; // 请求消息
	public static final int REQUST_REV_MSG = 3; // 添加好友后同意后返回消息
	public static final int LOGOUT_MSG = 4; // 退出消息
	public static final int UPDATE_FRIEND_MSG = 5; // 刷新好友列表消息
	public static final int UPDATE_DATA_MSG = 6; // 当好友修改资料后，及时更新好友列表类型的消息
	public static final int SELECT_PWD_MSG = 7; // 修改密码成功的消息
	public static final int SEND_FILE_MSG = 8; // 发送文件类型的消息
	public static final int SHAKE_MSG = 9; // 抖一抖
	
	private int type; // 消息类型
	private Account fromAccount; // 用来存储发送人
	private Account toAccount; // 用来存储接收人
	private Date sendTime; // 发送时间
	private String message; // 发送的消息
	
	private byte[] bytes;

	public Message() {
	}

	public Message(int type, Account fromAccount, Account toAccount, Date sendTime, String message) {
		this.type = type;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.sendTime = sendTime;
		this.message = message;
	}

	public Account getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Account getToAccount() {
		return toAccount;
	}

	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
}
