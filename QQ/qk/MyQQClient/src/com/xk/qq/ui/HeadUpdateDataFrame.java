package com.xk.qq.ui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.xk.bean.Account;
import com.xk.bean.Message;
import com.xk.common.DateUtil;
import com.xk.common.QQFont;
import com.xk.dao.AccountDAO;
import com.xk.dao.AccountDAOImpl;
import com.xk.qq.client.Client;
import com.xk.qq.ui.listener.ExitListener;
import com.xk.qq.ui.listener.FrameDragListener;
import com.xk.qq.ui.listener.MinListener;

public class HeadUpdateDataFrame extends JFrame{
	private static final long serialVersionUID = 7821476441593561248L;
	
	private Account account;
	private MainFrame mainFrame;
	private Client client;
	private JLabel autographLbl;
	private JComboBox<Integer> yearBox;
	private JComboBox<Integer> monthBox;
	private JComboBox<Integer> sunBox;
	private JTextField nickNameTxt;
	private JLabel ageLbl;
	private JComboBox<String> genderBox;
	private JComboBox<String> addressBox ;
	private JTextField contactTxt;
	private JTextField sinTxt;
	private JLabel headLbl;
	private JLabel nickNameLbl;
	private AlterHeadListener alterHead;
	private HeadFrame headFrame;
	private JLabel dayLbl;
	private JLabel genderLbl;
	private JLabel addressLbl2;
	
	public void setHeadFrame(HeadFrame headFrame) {
		this.headFrame = headFrame;
	}

	public HeadUpdateDataFrame(Account account,MainFrame mainFrame,Client client) {
		
		alterHead = new AlterHeadListener();
		this.account = account;
		this.mainFrame = mainFrame;
		this.client = client;
		setSize(400,544);
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
		JLabel bjLbl = new JLabel(new ImageIcon("images/check1.png"));
		bjLbl.setBounds(0, 0, 400, 200);
		add(bjLbl);
		
		JLabel minLbl = new JLabel(new ImageIcon("images/min_def.png"));
		minLbl.setName("Login_min");
		minLbl.addMouseListener(new MinListener(this));
		minLbl.setBounds(345, 0, 30, 30);
		bjLbl.add(minLbl);
		
		JLabel exitLbl = new JLabel(new ImageIcon("images/exit_def.png"));
		exitLbl.setBounds(370, 0, 30, 30);
		exitLbl.setName("Friend_exit");
		exitLbl.addMouseListener(new ExitListener(this,mainFrame));
		bjLbl.add(exitLbl);
		
		headLbl = new JLabel(new ImageIcon("images/"+account.getHeadIcon() + ".png"));
		headLbl.setBounds(20,110 , 70, 70);
		
		bjLbl.add(headLbl);
		
		nickNameLbl = new JLabel(account.getNickname());
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
		
		autographLbl = new JLabel(account.getAutograph());
		autographLbl.setBounds(100,160,150,30);
		bjLbl.add(autographLbl);
	}
	
	private void center(){
		
		JLabel headLbl1 = new JLabel(new ImageIcon("images/head1.png"));
		headLbl1.setBounds(45,240,70,70);
		add(headLbl1);
		
		JLabel headLbl2 = new JLabel(new ImageIcon("images/head2.png"));
		headLbl2.setBounds(125,240,70,70);
		add(headLbl2);
		
		JLabel headLbl3 = new JLabel(new ImageIcon("images/head3.png"));
		headLbl3.setBounds(205,240,70,70);
		add(headLbl3);
		
		JLabel headLbl4 = new JLabel(new ImageIcon("images/head4.png"));
		headLbl4.setBounds(285,240,70,70);
		add(headLbl4);
		
		JLabel numberLbl = new JLabel("账 号 ：");
		numberLbl.setBounds(25,310,60,20);
		add(numberLbl);
		
		JLabel numberLbl1 = new JLabel(account.getNumber());
		numberLbl1.setBounds(80,310,100,20);
		add(numberLbl1);
		
		JLabel nickNameLbl = new JLabel("昵 称 ：");
		nickNameLbl.setBounds(25,330,50,20);
		add(nickNameLbl);
		
		nickNameTxt = new JTextField(account.getNickname());
		nickNameTxt.setEditable(false);
		nickNameTxt.setBorder(null);
		nickNameTxt.setBounds(80,330,100,20);
		add(nickNameTxt);
		
		JLabel personageLbl = new JLabel("个 人 ：");
		personageLbl.setBounds(25,360,50,20);
		add(personageLbl);
		
	
		
		genderLbl = new JLabel(account.getGender());
		genderLbl.setBounds(80,360,50,20);	
		add(genderLbl);
		
		ageLbl = new JLabel(account.getAge()+"岁");
		ageLbl.setBounds(145,360,50,20);
		add(ageLbl);
		
		
		dayLbl = new JLabel(account.getBirthday());
		dayLbl.setBounds(190,360,200,20);
		add(dayLbl);
		
		
		JLabel addressLbl = new JLabel("所在地 ：");
		addressLbl.setBounds(20,390,55,20);
		add(addressLbl);
		
		addressLbl2 = new JLabel(account.getAdres());
		addressLbl2.setBounds(80,390,100,20);
		add(addressLbl2);
		
		
		
		JLabel contactLbl = new JLabel("联系方式 ：");
		contactLbl.setBounds(20,420,70,20);
		add(contactLbl);
		
		contactTxt = new JTextField(account.getContact());
		contactTxt.setBounds(85,420,150,20);
		contactTxt.setEditable(false);
		contactTxt.setBorder(null);
		add(contactTxt);
		
		JLabel sinLbl = new JLabel("个性签名 ： ");
		sinLbl.setBounds(20,450,75,20);
		add(sinLbl);
		
		sinTxt = new JTextField(account.getAutograph());
		sinTxt.setBounds(80,450,290,20);
		sinTxt.setEditable(false);
		sinTxt.setBorder(null);
		add(sinTxt);
		
		JButton btn = new JButton("编辑资料");
		btn.setBounds(250,205,80,30);
		btn.setActionCommand("compile");
		btn.addActionListener(new CompileActionListener(btn));
		btn.setBackground(new Color(0,0,0,0.3f));
		add(btn);
		
		JLabel southLbl = new JLabel(new ImageIcon("images/south.png"));
		southLbl.setBounds(0, 490, 400, 53);
		add(southLbl);
		
	}
	
	public void compile1(){
		yearBox = new JComboBox<Integer>();
		yearBox.setBounds(180,360,60,20);
		for(int i = 1900; i <=2016; i++){
			yearBox.addItem(i);
		}
		add(yearBox);
		
		monthBox = new JComboBox<Integer>();
		monthBox.setBounds(250,360,60,20);
		for(int i = 1; i <= 12; i++){
			monthBox.addItem(i);
		}
		add(monthBox);
		
		sunBox = new JComboBox<Integer>();
		sunBox.setBounds(320,360,60,20);
		for(int i = 1; i <= 31; i++){
			sunBox.addItem(i);
		}
		add(sunBox);
		
		genderBox = new JComboBox<String>();
		genderBox.setBounds(80,360,50,20);
		genderBox.addItem(account.getGender());
		genderBox.addItem("男");
		genderBox.addItem("女");
		add(genderBox);
		
		addressBox = new JComboBox<String>();
		addressBox.addItem(account.getAdres());
		addressBox.addItem("江西—南昌");
		addressBox.addItem("江西-赣州");
		addressBox.addItem("江西-九江");
		addressBox.addItem("江西-新余");
		addressBox.addItem("江西-上饶");
		addressBox.setBounds(80,390,100,20);
		add(addressBox);
	}
	
	public void compile2() {
		yearBox.setVisible(false);
		monthBox.setVisible(false);
		sunBox.setVisible(false);
		genderBox.setVisible(false);
		addressBox.setVisible(false);
		dayLbl.setText(account.getBirthday());
		genderLbl.setText(account.getGender());
		addressLbl2.setText(account.getAdres());
	}
	
	
	class CompileActionListener implements ActionListener {
		private JButton btn;
		public CompileActionListener(JButton btn){
			this.btn  = btn;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			if(action.equals("compile")){
				headLbl.addMouseListener(alterHead);
				JButton close = new JButton("关闭");
				btn.setText("保存");
				btn.setBounds(230, 205, 60, 30);
				btn.setActionCommand("save");
				btn.addActionListener(new SaveCloseActionListener(btn,close));
				
				close.setBounds(300, 205, 60, 30);
				close.addActionListener(new SaveCloseActionListener(btn,close));
				close.setActionCommand("close");
				add(close);
				
				dayLbl.setText("");
				genderLbl.setText("");
				addressLbl2.setText("");
				
				nickNameTxt.setEditable(true);
				contactTxt.setEditable(true);
				sinTxt.setEditable(true);
				compile1();
			}
		}
	}
	
	class SaveCloseActionListener implements ActionListener {
		private JButton saveBtn;
		private JButton closeBtn;
		private String action;
		
		private SaveCloseActionListener(JButton saveBtn,JButton closeBtn){
			this.saveBtn = saveBtn;
			this.closeBtn = closeBtn;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			action = e.getActionCommand();
			
			String strNickName = nickNameTxt.getText();
			String strGender = (String)genderBox.getSelectedItem();
			int intYearBox = (int) yearBox.getSelectedItem();
 			String strBirthday = yearBox.getSelectedItem() + "—" + (monthBox.getSelectedIndex() + 1) +"—" + (sunBox.getSelectedIndex()+1);
 			String strAddress = (String) addressBox.getSelectedItem();
 			String strContact = contactTxt.getText(); 
			String strSin = sinTxt.getText();
			if(action.equals("save")){
				if(strNickName != null && !strNickName.equals("") || strGender != null && !strGender.equals("") || 
				   strBirthday != null && !strBirthday.equals("") || strAddress != null && !strAddress.equals("") ||
				   strContact != null && !strContact.equals("") || strSin != null && strSin.equals("")){
					AccountDAO accounts = new AccountDAOImpl();
					account.setNickname(strNickName);
					account.setGender(strGender);
					account.setAge(2016-intYearBox);
					account.setBirthday(strBirthday);
					account.setContact(strContact);
					account.setAdres(strAddress);
					account.setAutograph(strSin);
					if(headFrame!= null){
						account.setHeadIcon(headFrame.getHeadName());
					}
					accounts.update(account);
					Account a = accounts.queryFriend(account.getNumber());
					if(a != null){
						updateData(account);
						mainFrame.updateDataMain(account);
						Message m = new Message(Message.STATES_CHANGE,DateUtil.getDate(),account,account,"资料修改为");
						client.sendMessage(m);
						closeBtn();
					}
				}
			}else if(action.equals("close")){
				headLbl.removeMouseListener(alterHead);
				updateData(account);
				closeBtn(); 
				
			}
		}
		private void closeBtn(){
			saveBtn.setActionCommand("compile");
			if(action.equals("compile")){
				saveBtn.addActionListener(new CompileActionListener(closeBtn));
			}
			headLbl.removeMouseListener(alterHead);
			saveBtn.setText("编辑资料");
			saveBtn.setBounds(250,205,80,30);
			closeBtn.setText("");
			closeBtn.setBounds(0, 0, 0, 0);
			
			nickNameTxt.setEditable(false);
			contactTxt.setEditable(false);
			sinTxt.setEditable(false);
			
			compile2();
		}  
		
	}
	
	public JLabel getHeadLbl() {
		return headLbl;
	}

	public void setHeadLbl(JLabel headLbl) {
		this.headLbl = headLbl;
	}

	private boolean isHead = false;
	class AlterHeadListener extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!isHead){
				new HeadFrame(HeadUpdateDataFrame.this);
				isHead = true;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			headLbl.setBorder(BorderFactory.createLineBorder(new Color(30,144,255)));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			headLbl.setBorder(null);
		}
		
	}
	class Close extends MouseAdapter{}
	
	public void updateData(Account account){
		ageLbl.setText(""+account.getAge());
		nickNameLbl.setText(account.getNickname());
		autographLbl.setText(account.getAutograph());
		headLbl.setIcon(new ImageIcon("images/"+ account.getHeadIcon()+ ".png"));
		
	}

	public boolean isHead() {
		return isHead;
	}

	public void setHead(boolean isHead) {
		this.isHead = isHead;
	}
	
}

