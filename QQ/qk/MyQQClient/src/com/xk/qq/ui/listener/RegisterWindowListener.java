package com.xk.qq.ui.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.xk.qq.ui.LoginFrame;
import com.xk.qq.ui.Register;

public class RegisterWindowListener extends WindowAdapter{
	
	@SuppressWarnings("unused")
	private Register register;
	private LoginFrame loginFrame;
	
	public RegisterWindowListener(Register register, LoginFrame loginFrame){
		this.register = register;
		this.loginFrame = loginFrame;
	}
	@Override
	public void windowClosing(WindowEvent e) {
		loginFrame.setRegister(false);
		
	}
}
