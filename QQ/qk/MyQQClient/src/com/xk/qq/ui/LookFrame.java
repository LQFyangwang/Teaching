package com.xk.qq.ui;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.xk.bean.Account;
import com.xk.common.QQFont;
import com.xk.qq.ui.common.MouseEnterExitIconUtil;
import com.xk.qq.ui.listener.FrameDragListener;
import com.xk.qq.ui.listener.MinListener;

public class LookFrame extends JFrame{
	
	private static final long serialVersionUID = 7821476441593561248L;
	private ChatFrame chatFrame;
	private Account account;
	
	private JLabel contactLbl2;
	private JLabel headLbl;
	public LookFrame(Account account,ChatFrame chatFrame) {
		this.chatFrame = chatFrame;
		this.account = account;
		setSize(400,533);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		top();
		center();
		setUndecorated(true);
		FrameDragListener darg = new FrameDragListener(this);
		addMouseListener(darg);
		addMouseMotionListener(darg);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/qq_icon.png"));
		setVisible(true);
	}
	
	private void top(){
		JLabel bjLbl = new JLabel(new ImageIcon("images/check.png"));
		bjLbl.setBounds(0, 0, 400, 200);
		add(bjLbl);
		
		JLabel minLbl = new JLabel(new ImageIcon("images/min_def.png"));
		minLbl.setName("Login_min");
		minLbl.addMouseListener(new MinListener(this,null,null));
		minLbl.setBounds(345, 0, 30, 30);
		bjLbl.add(minLbl);
		
		JLabel exitLbl = new JLabel(new ImageIcon("images/exit_def.png"));
		exitLbl.setBounds(370, 0, 30, 30);
		exitLbl.addMouseListener(new LookExitListener(exitLbl,this));
		bjLbl.add(exitLbl);
		
		headLbl = new JLabel(new ImageIcon("images/"+account.getHeadIcon() + ".png"));
		headLbl.setBounds(20,110 , 70, 70);
		bjLbl.add(headLbl);
		
		JLabel nickNameLbl = new JLabel(account.getNickname());
		nickNameLbl.setBounds(100,110,80,30);
		nickNameLbl.setFont(QQFont.myFont(18));
		bjLbl.add(nickNameLbl);
		
		JLabel numberLbl = new JLabel(account.getNumber());
		numberLbl.setBounds(180,115,80,20);
		numberLbl.setFont(QQFont.myFont(18));
		bjLbl.add(numberLbl);
		
		JLabel gradeLbl = new JLabel(new ImageIcon("images/grade1.png"));
		gradeLbl.setBounds(100,140,90,20);
		bjLbl.add(gradeLbl);
		
		JLabel autographLbl = new JLabel(account.getAutograph());
		autographLbl.setBounds(100,160,100,30);
		bjLbl.add(autographLbl);
	}
	
	private void center(){
		JLabel headLbl1 = new JLabel(new ImageIcon("images/head1.png"));
		headLbl1.setBounds(45,220,70,70);
		add(headLbl1);
		
		JLabel headLbl2 = new JLabel(new ImageIcon("images/head2.png"));
		headLbl2.setBounds(125,220,70,70);
		add(headLbl2);
		
		JLabel headLbl3 = new JLabel(new ImageIcon("images/head3.png"));
		headLbl3.setBounds(205,220,70,70);
		add(headLbl3);
		
		JLabel headLbl4 = new JLabel(new ImageIcon("images/head4.png"));
		headLbl4.setBounds(285,220,70,70);
		add(headLbl4);
		
		JLabel numberLbl = new JLabel("账 号 ：");
		numberLbl.setBounds(25,300,50,20);
		add(numberLbl);
		
		JLabel numberLbl1 = new JLabel(account.getNumber());
		numberLbl1.setBounds(80,300,100,20);
		add(numberLbl1);
		
		JLabel nickNameLbl = new JLabel("昵 称 ：");
		nickNameLbl.setBounds(25,330,50,20);
		add(nickNameLbl);
		
		JLabel nickNameLbl1 = new JLabel(account.getNickname());
		nickNameLbl1.setBounds(80,330,100,20);
		add(nickNameLbl1);
		
		JLabel personageLbl = new JLabel("个 人 ：");
		personageLbl.setBounds(25,360,50,20);
		add(personageLbl);
		
		JLabel personageLbl1 = new JLabel(account.getGender());
		personageLbl1.setBounds(80,360,50,20);
		add(personageLbl1);
		
		JLabel personageLbl2 = new JLabel(account.getAge()+"岁");
		personageLbl2.setBounds(140,360,50,20);
		add(personageLbl2);
		
		JLabel personageLbl3 = new JLabel(account.getBirthday());
		personageLbl3.setBounds(200,360,100,20);
		add(personageLbl3);
		
		JLabel addressLbl = new JLabel("所在地 ：");
		addressLbl.setBounds(20,390,55,20);
		add(addressLbl);
		
		JLabel addressLbl1 = new JLabel(account.getAdres());
		addressLbl1.setBounds(80,390,100,20);
		add(addressLbl1);
		
		JLabel contactLbl = new JLabel("联系方式 ：");
		contactLbl.setBounds(20,420,70,20);
		add(contactLbl);
		
		JLabel contactLbl2 = new JLabel(account.getContact());
		contactLbl2.setBounds(85,420,150,20);
		add(contactLbl2);
		
		JLabel qAgeLbl = new JLabel("Q  龄 ： ");
		qAgeLbl.setBounds(25,450,70,20);
		add(qAgeLbl);
		
		JLabel qAgeLbl1 = new JLabel("2 年");
		qAgeLbl1.setBounds(80,450,120,20);
		add(qAgeLbl1);
		
		JLabel southLbl = new JLabel(new ImageIcon("images/south.png"));
		southLbl.setBounds(0, 480, 400, 53);
		add(southLbl);
	}
	class LookExitListener extends MouseAdapter {
		private JLabel lbl;
		private LookFrame lookFrame;
		public LookExitListener(JLabel lbl,LookFrame lookFrame){
			this.lbl  =lbl;
			this.lookFrame = lookFrame;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			lookFrame.setVisible(false);
			if(chatFrame != null){
				lookFrame.dispose();
				chatFrame.setLook(false);
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
	public void updateLook(Account account){
		headLbl.setIcon(new ImageIcon("images/"+account.getHeadIcon() + ".png"));
	}
}
