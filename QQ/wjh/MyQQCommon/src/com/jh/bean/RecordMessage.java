package com.jh.bean;

import java.io.Serializable;
import java.util.Date;

public class RecordMessage implements Serializable{

	private static final long serialVersionUID = -3269456478784061170L;
	
	private String number; // 自己的账号
	private String toNumber; // 好友的账号
	private String messages; // 消息内容
	private Date sendTime; // 发送时间

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getToNumber() {
		return toNumber;
	}

	public void setToNumber(String toNumber) {
		this.toNumber = toNumber;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
}
