package com.gs.mail;

import javax.mail.Address;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Mail {
	
	public static final String HTML = "text/html";
	public static final String TEXT = "text/plain";
	
	private String from;
	private String recipients;
	private String ccRecipients;
	private String bccRecipients;
	private String subject;
	private String content;
	private String type;
	private Multipart multipart;
	public Address getFrom() {
		try {
			return InternetAddress.parse(from)[0];
		} catch (AddressException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public Address[] getRecipients() {
		try {
			return InternetAddress.parse(recipients);
		} catch (AddressException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}
	public Address[] getCcRecipients() {
		try {
			if (ccRecipients != null) {
				return InternetAddress.parse(ccRecipients);
			}
		} catch (AddressException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void setCcRecipients(String ccRecipients) {
		this.ccRecipients = ccRecipients;
	}
	public Address[] getBccRecipients() {
		try {
			if (bccRecipients != null) {
				return InternetAddress.parse(bccRecipients);
			}
		} catch (AddressException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void setBccRecipients(String bccRecipients) {
		this.bccRecipients = bccRecipients;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Multipart getMultipart() {
		return multipart;
	}
	public void setMultipart(Multipart multipart) {
		this.multipart = multipart;
	}

}
