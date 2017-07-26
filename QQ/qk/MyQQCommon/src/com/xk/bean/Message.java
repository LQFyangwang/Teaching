package com.xk.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * QQ消息类，保存有发送人，接收人，发送时间和发送的消息
 *
 */
public class Message implements Serializable{

	@Override
	public String toString() {
		return "Message [type=" + type + ", sendTime=" + sendTime + ", fromAccount=" + fromAccount + ", toAccount="
				+ toAccount + ", message=" + message + "]";
	}
	private static final long serialVersionUID = -4996033880257787456L;
	
	public static final int NORMAL_MSG = 1; // 正常聊天消息
	public static final int REQUEST_MSG = 2; // 好友请求消息
	public static final int REQUEST_REV_MSG = 3; // 接收好友请求消息
	public static final int LOGOUT_MSG = 4; // 退出
	public static final int STATES_CHANGE = 5; // 状态消息 
	public static final int DELETE_MSG = 6; 	//删除消息
	public static final int BLACKLIST_MSG = 7; // 添加至黑名单消息
	public static final int SHAKE_MSG = 8; // 窗口抖动消息
	
	private int type;				// 消息类型
	private Date sendTime; 			// 发送消息的时间
	private Account fromAccount;	// 发送人
	private Account toAccount;		// 接收人
	private String message; 		// 接收的消息
	
	public Message() {
		
	}
	
	public Message(int type,Date sendTime, Account fromAccount, Account toAccount, String message) {
		this.type = type;
		this.sendTime = sendTime;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.message = message;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
