package com.xk.qq.ui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.xk.qq.ui.HeadUpdateDataFrame;
import com.xk.qq.ui.LoginFrame;
import com.xk.qq.ui.MainFrame;
import com.xk.qq.ui.common.MouseEnterExitIconUtil;

public class ExitListener extends MouseAdapter{

	private JFrame frame;
	private MainFrame mainFrame;
	private HeadUpdateDataFrame hudFrame;
	private LoginFrame loginFrame;	
	public ExitListener(JFrame frame){
		this.frame = frame;
	}
	public ExitListener(JFrame frame,MainFrame mainFrame ){
		this.frame = frame;
		this.mainFrame = mainFrame;
	}
	public ExitListener(JFrame frame,HeadUpdateDataFrame hudFrame){
		this.frame = frame;
		this.hudFrame = hudFrame;
	}
	
	public ExitListener(JFrame frame,LoginFrame loginFrame){
		this.frame = frame;
		this.loginFrame = loginFrame;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JLabel){
			JLabel lbl = (JLabel)obj;
			String name = lbl.getName();
			if(name.equals("LoginMain_exit")){
				System.exit(0);
			}else if(name.equals("Friend_exit")){
				frame.dispose();
				mainFrame.setSearchOpen(false);
				mainFrame.setUpdateData(false);
				mainFrame.setAlterSkin(false);
			}else if(name.equals("dispose_exit")){
				frame.dispose();
				loginFrame.setRegister(false);
			}else if(name.equals("Head_exit")){
				frame.dispose();
				hudFrame.setHead(false);
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		MouseEnterExitIconUtil.change(e, "images/exit_over.png");
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		MouseEnterExitIconUtil.change(e,"images/exit_def.png");
	}


	
}
