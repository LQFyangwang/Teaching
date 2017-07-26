package com.xk.qq.ui;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;

import com.xk.common.QQFont;
import com.xk.qq.ui.listener.ExitListener;
import com.xk.qq.ui.listener.FrameDragListener;
import com.xk.qq.ui.listener.HeadItemListener;
import com.xk.qq.ui.listener.LoginListener;
import com.xk.qq.ui.listener.MinListener;
import com.xk.qq.ui.listener.StatusListener;
import com.xk.qq.ui.listener.TrigonListener;



public class LoginFrame extends JFrame{
	
	private static final long serialVersionUID = -8414311112991221577L;
	
	private boolean isRegister = false;
	private JLabel headLbl;
	
	
	public boolean isRegister() {
		return isRegister;
	}

	public void setRegister(boolean isRegister) {
		this.isRegister = isRegister;
	}

	public LoginFrame(){
		try {
			LookAndFeelInfo feels[] = UIManager.getInstalledLookAndFeels();
			UIManager.setLookAndFeel(feels[3].getClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setSize(430,340);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setUndecorated(true);//Òþ²Ø´°¿Ú
		top();
		bottom();
		FrameDragListener darg = new FrameDragListener(this);
		addMouseListener(darg);
		addMouseMotionListener(darg);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/qq_icon.png"));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void top(){
		ImageIcon icon = new ImageIcon("images/background1.png");
		JLabel lbl = new JLabel(icon);
		new Thread(new BackgroundChangeThread(lbl)).start();
		lbl.setBounds(0,0,450,200);
		add(lbl);
		
		ImageIcon triangIcon = new ImageIcon("images/triangle_def.png");
		JLabel triangLbl = new JLabel(triangIcon);
		triangLbl.addMouseListener(new TrigonListener());
		triangLbl.setBounds(345, 0, 30, 30);
		lbl.add(triangLbl);
		
		ImageIcon minIcon = new ImageIcon("images/min_def.png");
		JLabel minLbl = new  JLabel(minIcon);
		minLbl.setName("Login_min");
		minLbl.addMouseListener(new MinListener(this));
		minLbl.setBounds(375,0, 30, 30);
		lbl.add(minLbl);
		
		ImageIcon exitIcon = new ImageIcon("images/exit_def.png");
		JLabel exitLbl = new JLabel(exitIcon);
		exitLbl.setName("LoginMain_exit");
		exitLbl.addMouseListener(new ExitListener(this));
		exitLbl.setBounds(400,0, 30, 30);
		lbl.add(exitLbl);
	}
	
	private void bottom(){
		ImageIcon headIcon = new ImageIcon("images/head.png");
		headLbl = new JLabel(headIcon);
		headLbl.setBounds(30, 220, 80, 80);
		add(headLbl);
		
		ImageIcon statusIcon = new ImageIcon("images/online.png");
		JLabel statusLbl = new JLabel(statusIcon);
		statusLbl.addMouseListener(new StatusListener(headLbl,statusLbl));
		statusLbl.setBounds(60, 60, 13, 13);
		headLbl.add(statusLbl);
		
		JLabel accLbl = new JLabel("ÕËºÅ £º");
		accLbl.setFont(QQFont.myFont(12));
		accLbl.setBounds(130,220,45,25);
		add(accLbl);
		
		JComboBox<String> accBox = new JComboBox<String>();
		accBox.setBounds(175,220,160,25);
		accBox.addItemListener(new HeadItemListener(headLbl,accBox));
		accBox.setEditable(true); // ÉèÖÃÏÂÀ­²Ëµ¥ÊÇ·ñ¿É±à¼­£¬trueÎª¿É±à¼­£¬Ä¬ÈÏÎªfalse£¬²»¿É±à¼­
		add(accBox);
		
		JLabel regLbl = new JLabel("ÉêÇëÕËºÅ");
		regLbl.setFont(QQFont.myFont(12));
		regLbl.setForeground(Color.BLUE);
		regLbl.setName("reg");
		regLbl.addMouseListener(new ChangeColor(regLbl));
		regLbl.setBounds(343,220,55,25);
		add(regLbl);
		
		JLabel pwdLbl = new JLabel("ÃÜÂë £º");
		pwdLbl.setFont(QQFont.myFont(12));
		pwdLbl.setBounds(130,250,45,25);
		add(pwdLbl);
		
		JPasswordField pwdTxt = new JPasswordField();
		pwdTxt.setBounds(175, 250, 160, 25);
		add(pwdTxt);
		
		JLabel foLbl = new JLabel("Íü¼ÇÃÜÂë");
		foLbl.setFont(QQFont.myFont(12));
		foLbl.setForeground(Color.BLUE);
		foLbl.setName("fo");
		foLbl.addMouseListener(new ChangeColor(foLbl));
		foLbl.setBounds(343,250,55,25);
		add(foLbl);
		
		JCheckBox remBox = new JCheckBox("¼Ç×¡ÃÜÂë");
		remBox.setFont(QQFont.myFont(12));
		remBox.setForeground(Color.GRAY);
		remBox.setBounds(150,278,100,25);
		add(remBox);
		
		JCheckBox autBox = new JCheckBox("×Ô¶¯µÇÂ¼");
		autBox.setFont(QQFont.myFont(12));
		autBox.setForeground(Color.GRAY);
		autBox.setBounds(260,278,100,25);
		add(autBox);
		
		ImageIcon loinIcon = new ImageIcon("images/login_btn_def.png");
		JLabel loinLbl = new JLabel(loinIcon);
		loinLbl.addMouseListener(new LoginListener(this,accBox,pwdTxt,loinLbl));
		loinLbl.setBounds(130, 305, 194,30);
		add(loinLbl);
		
	}
	
	class BackgroundChangeThread implements Runnable {
		private JLabel label;
		public BackgroundChangeThread(JLabel label){
			this.label = label;
		}
		
		@Override
		public void run() {
			int count = 2;
			while(true){
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				label.setIcon(new ImageIcon("images/background" + count + ".png" ));
				count++;
				if(count > 3){
					count = 1;
				}
				
			}
		}
		
	}
	
	public class ChangeColor extends MouseAdapter{
		private JLabel lbl;
		public ChangeColor(JLabel lbl){
			this.lbl = lbl;
		}
		
		
		@Override
		public void mouseClicked(MouseEvent e) {
			Object obj = e.getSource();
			if(obj instanceof JLabel){
				JLabel lbl = (JLabel)obj;
				String name = lbl.getName();
				if(name.equals("reg")){
					if(!isRegister){
						new Register(LoginFrame.this);
						isRegister = true;
					}
					
				}else if(name.equals("fo")){
					
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			lbl.setForeground(Color.MAGENTA);
			lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			lbl.setForeground(Color.BLUE);
			lbl.setCursor(Cursor.getDefaultCursor());
		}
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				new LoginFrame();
			}
			
		});
	}
	
	
}
