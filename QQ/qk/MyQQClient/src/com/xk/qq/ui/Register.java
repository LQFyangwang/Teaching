package com.xk.qq.ui;


import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.xk.bean.Account;
import com.xk.common.QQFont;
import com.xk.dao.AccountDAO;
import com.xk.dao.AccountDAOImpl;
import com.xk.qq.ui.common.CheckCode;
import com.xk.qq.ui.common.CheckCodeGenerator;
import com.xk.qq.ui.listener.ExitListener;
import com.xk.qq.ui.listener.FrameDragListener;
import com.xk.qq.ui.listener.MinListener;
import com.xk.qq.ui.listener.RegisterWindowListener;
import com.xk.qq.ui.listener.StatusActionListner;

public class Register extends JFrame{
	
	private static final long serialVersionUID = -8414311112991221577L;
	
	private JTextField nameTxt;
	private JPasswordField pwdTxt;
	private JPasswordField pwdTxtOne;
	private JTextField numberTxt;
	private CheckboxGroup cbg;
	private JTextField codeTxt;
	private String codeStr;
	private CheckCode check;
	private JComboBox<Integer> yearBox;
	private JComboBox<Integer> monthBox;
	private JComboBox<Integer> sunBox;
	private LoginFrame loginFrame;
	
	public Register(LoginFrame loginFrame){
		this.loginFrame = loginFrame;
		setSize(500,515);
		setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		initWidget();
	
		
		setUndecorated(true);//隐藏窗口
		FrameDragListener darg = new FrameDragListener(this);
		addMouseListener(darg);
		addMouseMotionListener(darg);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/qq_icon.png"));
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setVisible(true);
	}
	
	public void initWidget(){
		
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(new Color(30,144,255));
		pan.setBounds(0, 0, 500, 40);
		
		JLabel qqIcon = new JLabel(new ImageIcon("images/tubiao.png"));
		qqIcon.setBounds(5, 5, 35, 23);
		pan.add(qqIcon);
		
		ImageIcon minIcon = new ImageIcon("images/min_def.png");
		JLabel minLbl = new  JLabel(minIcon);
		minLbl.setName("Login_min");
		minLbl.addMouseListener(new MinListener(this));
		minLbl.setBounds(440,0, 30, 30);
		pan.add(minLbl);
		
		ImageIcon exitIcon = new ImageIcon("images/exit_def.png");
		JLabel exitLbl = new JLabel(exitIcon);
		exitLbl.setName("dispose_exit");
		exitLbl.addMouseListener(new ExitListener(this,loginFrame));
		exitLbl.setBounds(470,0, 30, 30);
		pan.add(exitLbl);
		add(pan);
		
		JLabel titleLbl = new JLabel("注册账号");
		titleLbl.setFont(QQFont.myFont(20));
		titleLbl.setBounds(40,55,100,50);
		add(titleLbl);
		
		
		JLabel nameLbl = new JLabel("昵   称 ：");
		nameLbl.setFont(QQFont.myFont(15));
		nameLbl.setBounds(90,100,70,35);
		add(nameLbl);
		
		nameTxt = new JTextField();
		nameTxt.setFont(QQFont.myFont(15));
		nameTxt.setBounds(150,100,270,35);
		add(nameTxt);
		
		JLabel pwdLbl = new JLabel("密   码 ：");
		pwdLbl.setFont(QQFont.myFont(15));
		pwdLbl.setBounds(90,145,70,35);
		add(pwdLbl);
		
		pwdTxt = new JPasswordField();
		pwdTxt.setFont(QQFont.myFont(15));
		pwdTxt.setBounds(150,145,270,35);
		add(pwdTxt);
		
		JLabel pwdLblOne = new JLabel("确认密码 ：");
		pwdLblOne.setFont(QQFont.myFont(15));
		pwdLblOne.setBounds(70,190,85,35);
		add(pwdLblOne);
		
		pwdTxtOne = new JPasswordField();
		pwdTxtOne.setFont(QQFont.myFont(15));
		pwdTxtOne.setBounds(150,190,270,35);
		add(pwdTxtOne);
		
		JLabel dayLbl = new JLabel("生  日 ：");
		dayLbl.setFont(QQFont.myFont(15));
		dayLbl.setBounds(90,240,60,35);
		add(dayLbl);
		
		yearBox = new JComboBox<Integer>();
		yearBox.setBounds(160,240,70,30);
		yearBox.setFont(QQFont.myFont(14));
		for(int i = 1900; i <=2016; i++){
			yearBox.addItem(i);
		}
		add(yearBox);
		
		monthBox = new JComboBox<Integer>();
		monthBox.setBounds(250,240,70,30);
		monthBox.setFont(QQFont.myFont(14));
		for(int i = 1; i <= 12; i++){
			monthBox.addItem(i);
		}
		add(monthBox);
		
		sunBox = new JComboBox<Integer>();
		sunBox.setBounds(340,240,70,30);
		sunBox.setFont(QQFont.myFont(14));
		for(int i = 1; i <= 31; i++){
			sunBox.addItem(i);
		}
		add(sunBox);
		
	
		
		JLabel addressLbl = new JLabel("地   址 ：");
		addressLbl.setFont(QQFont.myFont(15));
		addressLbl.setBounds(85,285,70,35);
		add(addressLbl);
		
		JComboBox<String> province = new JComboBox<String>();
		province.setBounds(160,285,70,30);
		province.setFont(QQFont.myFont(14));
		String[] provinceName = new String[]{"省份","北京市","天津市","上海市","重庆市","河北省",	"山西省",
											 "内蒙古","辽宁省","吉林省","黑龙江省","江苏省","浙江省","安徽省",
											 "福建省","四川省","湖南省","湖北省","江西省","贵州省",};
		for(int i = 0,len = provinceName.length; i < len; i++){
			province.addItem(provinceName[i]);
		}
		add(province);
		
		JComboBox<String> city = new JComboBox<String>();
		city.setBounds(250,285,70,30);
		city.setFont(QQFont.myFont(14));
		String[] cityName = new String[]{"城市","北京市","天津市","上海市","重庆市","石家庄","张家口","承德市",
										 "太原市","大同市","晋城市","乌海市","包头市","沈阳市","铁岭市",
										 "辽阳市","长春市","白城市","松原市","哈尔滨","齐哈尔","七台河","南京市",
										 "徐州市","盐城市","杭州市","温州市","绍兴市","合肥市","宿州市","淮北市","城都市",
										 "广元市","绵阳市","九江市","景德镇","赣州市","南昌市"};
		for(int i = 0, len = cityName.length; i < len; i++){
			city.addItem(cityName[i]);
		}
		add(city);
		
		JComboBox<String> area = new JComboBox<String>();
		area.setBounds(340,285,70,30);
		area.setFont(QQFont.myFont(14));
		String[] areaName = new String[]{"区域" ,"东城区","西城区" ,"崇文区","宣武区","朝阳区","丰台区","石景山  ",
										 "海淀区","门头沟","房山区 ","顺义区","昌平区","平谷区","怀柔区","延庆县",
										 "南康县","石城县","信丰县","兴国县","寻乌县","瑞金县","新华区","郊  区","井陉矿",
										 "井陉县","正定县","栾城县","行唐县","灵寿县","高邑县","深泽县","赞皇县","无极县"};
		for(int i = 0,len = areaName.length; i < len; i++){
			area.addItem(areaName[i]);
		}
		add(area);
		
		JLabel genLbl = new JLabel("性    别 ：");
		genLbl.setFont(QQFont.myFont(15));
		genLbl.setBounds(85,325,70,35);
		add(genLbl);
		
		cbg = new CheckboxGroup();
		Checkbox man = new Checkbox("男",cbg,true);
		man.setFont(QQFont.myFont(15));
		man.setBounds(170,325,60,30);
		add(man);
		
		Checkbox woman = new Checkbox("女",cbg,false);
		woman.setBounds(250,325,60,30);
		woman.setFont(QQFont.myFont(15));
		add(woman);
		
		JLabel numberLbl = new JLabel("手机号码 ：");
		numberLbl.setFont(QQFont.myFont(15));
		numberLbl.setBounds(70,360,95,35);
		add(numberLbl);
		
		numberTxt = new JTextField();
		numberTxt.setFont(QQFont.myFont(15));
		numberTxt.setBounds(150,360,270,35);
		add(numberTxt);
		
		JLabel codeLbl = new JLabel(" 验证码 ：");
		codeLbl.setFont(QQFont.myFont(15));
		codeLbl.setBounds(80,410,70,35);
		add(codeLbl);
		
		codeTxt = new JTextField();
		codeTxt.setFont(QQFont.myFont(15));
		codeTxt.setBounds(150,410,70,35);
		add(codeTxt);
		
		check = CheckCodeGenerator.getCheckCode();
		JLabel checkLbl = new JLabel(new ImageIcon(check.getCheckImage()));
		codeStr = check.getCheck();
		checkLbl.setBounds(245, 410, 70, 35);
		add(checkLbl);
		
		JLabel tradeLbl = new JLabel("换一张");
		tradeLbl.setBounds(330,410,70,35);
		tradeLbl.addMouseListener(new CodeMouseListener(checkLbl,tradeLbl));
		tradeLbl.setName("code");
		tradeLbl.setFont(QQFont.myFont(15));
		add(tradeLbl);
		
		JLabel yesLbl = new JLabel(new ImageIcon("images/register_def.png"));
		yesLbl.setBounds(95,455,304,50);
		yesLbl.addMouseListener(new RegisterListener(yesLbl));
		add(yesLbl);
		
		
		
	}
	

	
	class CodeMouseListener extends MouseAdapter {
		private JLabel lbl;
		private JLabel statuLbl;
		public CodeMouseListener(JLabel lbl, JLabel statuLbl){
			this.lbl = lbl;
			this.statuLbl = statuLbl;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			Object obj = e.getSource();
			if(obj instanceof JLabel){ 
				JLabel code = (JLabel)obj;
				String name = code.getName();
				if(name.equals("code")){
					check = CheckCodeGenerator.getCheckCode();
					lbl.setIcon(new ImageIcon(check.getCheckImage()));
				}
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			statuLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			statuLbl.setCursor(Cursor.getDefaultCursor());
		}
	
		
	}
	
	class RegisterListener extends MouseAdapter{
		private JLabel lbl;
		public RegisterListener(JLabel lbl) {
			this.lbl = lbl;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			String strName = nameTxt.getText();
			String strPwd = new String(pwdTxt.getPassword());
			String strPwdOne = new String(pwdTxtOne.getPassword()); 
			int intYearBox = (int) yearBox.getSelectedItem();
			String strNumber = numberTxt.getText(); 
 			String strGen = cbg.getSelectedCheckbox().getLabel();
 			String strCode = codeTxt.getText();
 			String strBirthday = yearBox.getSelectedItem() + "—" + (monthBox.getSelectedIndex() + 1) +"—" + (sunBox.getSelectedIndex()+1);
 			
			if(strName != null && !strName.equals("") && strPwd != null && !strPwd.equals("") && 
					   strPwdOne != null && !strPwdOne.equals("") && strNumber != null && !strNumber.equals("")&&
					   strCode != null && !strCode.equals("")){
						if(strPwd.equals(strPwdOne) && strCode.equalsIgnoreCase((String.valueOf(codeStr)))){
							
							Account account = new Account();
							AccountDAO a = new AccountDAOImpl();
							String number = String.valueOf(new Random().nextInt(9999999));
							if(!a.query(number)){
								number = String.valueOf(new Random().nextInt(9999999));
							}
							account.setNumber(number);
							account.setPwd(strPwd);
							account.setNickname(strName);
							account.setAutograph("编辑个性签名");
							account.setGender(strGen);
							account.setAge(2016 - intYearBox);
							account.setAdres("江西—赣州");
							account.setContact(strNumber);
							account.setBirthday(strBirthday);
							account.setHeadIcon("head");
							account.setState("offline");
							Account accounts = a.add(account);
							
							if(accounts != null){
								JOptionPane.showMessageDialog(Register.this, "注册成功  " + "账号：" + number,"警告",JOptionPane.PLAIN_MESSAGE);	
							}
						}else {
							JOptionPane.showMessageDialog(Register.this, "注册失败,验证码或两次密码不一样","警告",JOptionPane.PLAIN_MESSAGE);
						}
						
					}else {
						JOptionPane.showMessageDialog(Register.this, "昵称,密码,手机号,验证码不可以为空！","警告",JOptionPane.PLAIN_MESSAGE);
					}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
//			lbl.setIcon(new ImageIcon("images/register_over.png"));
			lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		@Override
		public void mouseExited(MouseEvent e) {
//			lbl.setIcon(new ImageIcon("images/register_def.png"));
			lbl.setCursor(Cursor.getDefaultCursor());
		}
		
	}
	

	
}
