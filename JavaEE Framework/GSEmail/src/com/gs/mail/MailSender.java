package com.gs.mail;

import java.io.File;
import java.util.Properties;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

public class MailSender {
	
	public void sendEmail(String path, Mail mail) {
		Properties props = MailConfig.readProperties(path);
		Session session = Session.getInstance(props, new MailAuth(MailConfig.getString("username"), MailConfig.getString("password")));
		try {
			Transport transport = session.getTransport();
			transport.connect();
			Message msg = new MimeMessage(session);
			mail.setFrom(MailConfig.getString("username"));
			msg.setFrom(mail.getFrom());
			msg.setSubject(mail.getSubject());
			if (mail.getContent() != null) {
				msg.setContent(mail.getContent(), mail.getType());
			} else {
				msg.setContent(mail.getMultipart());
			}
			msg.setRecipients(RecipientType.TO, mail.getRecipients());
			if (mail.getCcRecipients() != null) {
				msg.setRecipients(RecipientType.CC, mail.getCcRecipients());
			}
			if (mail.getBccRecipients() != null) {
				msg.setRecipients(RecipientType.BCC, mail.getBccRecipients());
			}
			transport.send(msg);
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws MessagingException {
		MailSender mailSender = new MailSender();
		Mail mail = new Mail();
		mail.setSubject("subject");
		mail.setRecipients("wgssmarter@126.com,847315251@qq.com");
		Multipart multipart = new MimeMultipart();
		BodyPart part1 = new MimeBodyPart();
		part1.setContent("您已注册成功，请点击以下链接完成激活：<a href=' http://115.28.37.189?email=abc@126.com&code=" + UUID.randomUUID().toString() + "'>激活你的账号</a>", "text/html;charset=utf-8");
		multipart.addBodyPart(part1);
		BodyPart part2 = new MimeBodyPart();
		DataHandler dataHandler = new DataHandler(new FileDataSource(new File("src/360wallpaper.jpg")));
		part2.setDataHandler(dataHandler);
		multipart.addBodyPart(part2);
		BodyPart part3 = new MimeBodyPart();
		DataHandler dataHanler1 = new DataHandler(new FileDataSource(new File("src/JavaMail.txt")));
		part3.setDataHandler(dataHanler1);
		multipart.addBodyPart(part3);
		mail.setMultipart(multipart);
		mailSender.sendEmail("mail.properties", mail);
	}
	
}


