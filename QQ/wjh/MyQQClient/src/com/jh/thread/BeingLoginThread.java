package com.jh.thread;

import javax.swing.JFrame;

import com.jh.bean.Account;
import com.jh.client.Client;
import com.jh.ui.ErrorFrame;
import com.jh.ui.MainFrame;

/**
 * 这个线程用来做一个过渡的效果，延迟5s钟再去判断账号密码是否正确，
 * 然后new出对应的窗体，如果账号密码正确，则new出主窗体，否则new出出错窗体
 * @author Administrator
 *
 */
public class BeingLoginThread implements Runnable {
	
	private JFrame frame;
	private Account account;
	public static boolean isOk = true;
	
	public BeingLoginThread(JFrame frame, Account account) {
		this.frame = frame;
		this.account = account;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (isOk) {
			frame.dispose();
			if (account != null) { // 判断账号是否存在
				Client client = new Client();
				client.login(account);
				new MainFrame(account, client);
			} else {
				new ErrorFrame();
			}
		}
		
	}

}
