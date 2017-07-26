package com.xk.qq.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.xk.bean.Account;
import com.xk.bean.Message;
import com.xk.common.DateUtil;
import com.xk.qq.client.Client;
import com.xk.qq.ui.common.MouseEnterExitIconUtil;
import com.xk.qq.ui.listener.BorderListener;
import com.xk.qq.ui.listener.ChatFrameBtnListener;
import com.xk.qq.ui.listener.ExitListener;
import com.xk.qq.ui.listener.FrameDragListener;
import com.xk.qq.ui.listener.MinListener;
import com.xk.qq.ui.listener.ShakeListener;

public class ChatFrame extends JFrame{

	private static final long serialVersionUID = -8004540687052629171L;
	
	private JTextArea  area;
	private JTextArea centerArea;
	private Account account;
	private Account toAccount;
	private Client client;
	private JLabel shakeLbl;
	

	public JLabel getShakeLbl() {
		return shakeLbl;
	}

	public JTextArea getArea() {
		return area;
	}

	public JTextArea getCenterArea() {
		return centerArea;
	}

	public Account getAccount() {
		return account;
	}

	public Account getToAccount() {
		return toAccount;
	}

	public ChatFrame(Client client,Account account,Account toAccount){
		this.client = client;
		this.account = account;
		this.toAccount = toAccount;
		setSize(550,500);
		setLocation(350,100);
		getContentPane().setLayout(null);
		top();
		center();
		south();
		left();
		setUndecorated(true);
		FrameDragListener darg = new FrameDragListener(this);
		addMouseListener(darg);
		addMouseMotionListener(darg);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/qq_icon.png"));
		setVisible(true);
	}

	
	private void top(){
		JLabel headLbl = new JLabel(new ImageIcon("images/"+ toAccount.getHeadIcon()+".png"));
		headLbl.setBounds(10, 10, 60, 60);
		headLbl.addMouseListener(new HeadListener(headLbl));
		add(headLbl);
		
		JLabel numberLbl = new JLabel(toAccount.getNickname());
		numberLbl.setBounds(80, 15, 120, 30);
		add(numberLbl);
		
		JLabel spaceLbl = new JLabel(new ImageIcon("images/space.png"));
		spaceLbl.setBounds(80, 45, 20, 20);
		spaceLbl.addMouseListener(new BorderListener(spaceLbl));
		add(spaceLbl);
		
		if(!toAccount.getAutograph().equals("编辑个性签名")){
			JLabel sinLbl = new JLabel(toAccount.getAutograph());
			sinLbl.setBounds(105, 40, 120, 25);
			add(sinLbl);
		}
		
		JLabel topLbl1 = new JLabel(new ImageIcon("images/top_01.png"));
		topLbl1.setBounds(0, 67, 40, 40);
		add(topLbl1);
		
		int x = 40;
		for(int i = 2; i <= 7; i++){
			JLabel topLbl = new JLabel(new ImageIcon("images/top_0" + i + ".png"));
			topLbl.setBounds(x, 67, 40, 40);
			add(topLbl);
			x +=60;
		}
		
	
		
		JLabel minLbl = new JLabel(new ImageIcon("images/min_def.png"));
		minLbl.setName("Login_min");
		minLbl.addMouseListener(new MinListener(this));
		minLbl.setBounds(490, 0, 30, 30);
		add(minLbl);
		
		JLabel exitLbl = new JLabel(new ImageIcon("images/exit_def.png"));
		exitLbl.setBounds(520, 0, 30, 30);
		exitLbl.addMouseListener(new ChatExitListener(exitLbl,this));
		add(exitLbl);
		
	}
	
	private void left(){
		JLabel leftLbl = new JLabel(new ImageIcon("images/ad.png"));
		leftLbl.setBounds(421, 85, 129, 495);
		add(leftLbl);
	}
	
	private void center() {
		
		centerArea = new JTextArea();
		centerArea.setOpaque(false);
		centerArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(centerArea);
		scrollPane.setBorder(null);
		scrollPane.setBounds(0,102,420,190);
		add(scrollPane);
		
		shakeLbl =  new JLabel("");
		shakeLbl.setBounds(130, 290, 100, 25);
		add(shakeLbl);
		
	}
	
	private void south(){
		int x = 0;
		for(int i = 1; i <= 9; i++){
			JLabel bottomLbl = new JLabel(new ImageIcon("images/bottom_0" + i + ".png"));
			bottomLbl.setBounds(x, 315, 40, 25);
			bottomLbl.addMouseListener(new ShakeListener(bottomLbl,this,client));
			bottomLbl.setName("bottom" + i);
			add(bottomLbl);
			x +=40;
		}
		
		
		area = new JTextArea(" ");
		area.setOpaque(false);
		area.setBorder(null);
		JScrollPane scrollPane = new JScrollPane(area);
		scrollPane.setBorder(null);
		scrollPane.setBounds(0,340,420,130);
		
		JLabel closeLbl = new JLabel(new ImageIcon("images/close_def.png"));
		closeLbl.setBounds(280,470,62,25);
		closeLbl.addMouseListener(new CloseMouseListener(closeLbl,this));
		add(closeLbl);
		
		JButton btnSend = new JButton(new ImageIcon("images/send_def.png"));
		btnSend.setBounds(350,470,62,25);
		btnSend.setBorder(null);
		btnSend.addMouseListener(new SendMouseListener(btnSend));
		btnSend.setActionCommand("send");
		btnSend.addActionListener(new ChatFrameBtnListener(client,this));
		add(btnSend);
		add(scrollPane);
	}
	
	public void updateMessage(Message message){
		centerArea.setText(centerArea.getText() + "\n" + message.getFromAccount().getNickname() + " 说 :" + message.getMessage() + "  接收时间：" +  DateUtil.getTime());
		
	}
	
	public void updateShake(){
		for(int i = 0; i < 15; i++){
			for(int j = 0 ; j < 15; j++){
				Rectangle bound = getBounds();
				setBounds(bound.x+7, bound.y+7, 550, 500);
				setBounds(bound.x, bound.y, 550, 500);
				
				setBounds(bound.x-7, bound.y -7, 550, 500);
				setBounds(bound.x,bound.y, 550, 500);
			}
			
		}
	}
	
	class SendMouseListener extends MouseAdapter{
		private JButton btn;
		
		public SendMouseListener(JButton  btn){
			this.btn = btn;
			 
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			btn.setIcon(new ImageIcon("images/send_over.png"));
			btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			btn.setIcon(new ImageIcon("images/send_def.png"));
			btn.setCursor(Cursor.getDefaultCursor());
		}
		
	}
	
	
	
	class CloseMouseListener extends MouseAdapter{
		private JLabel lbl;
		private ChatFrame chatFrame;
		public CloseMouseListener(JLabel lbl,ChatFrame chatFrame){
			this.lbl = lbl;
			this.chatFrame = chatFrame;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			chatFrame.setVisible(false);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			lbl.setIcon(new ImageIcon("images/close_over.png"));
			lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			lbl.setIcon(new ImageIcon("images/close_def.png"));
			lbl.setCursor(Cursor.getDefaultCursor());
		}
		
	}
	
	class ChatExitListener extends MouseAdapter{
		private ChatFrame chatFrame;
		private JLabel lbl;
		public ChatExitListener(JLabel lbl,ChatFrame chatFrame){
			this.chatFrame= chatFrame;
			this.lbl = lbl;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			chatFrame.setVisible(false);
			shakeLbl.setText("");
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			MouseEnterExitIconUtil.change(e, "images/exit_over.png");
		}
		@Override
		public void mouseExited(MouseEvent e) {
			MouseEnterExitIconUtil.change(e, "images/exit_def.png");
		}
		
	}
	
	private boolean isLook = false;
	
	public void setLook(boolean isLook) {
		this.isLook = isLook;
	}

	class HeadListener extends MouseAdapter{
		private JLabel lbl;
		public HeadListener(JLabel lbl){
			this.lbl = lbl;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!isLook){
				new LookFrame(toAccount,ChatFrame.this);
				isLook = true;
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			lbl.setCursor(Cursor.getDefaultCursor());
		}
		
		
	}
}
