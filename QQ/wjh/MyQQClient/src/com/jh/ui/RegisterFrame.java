package com.jh.ui;

import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;

import com.jh.common.Constants;
import com.jh.ui.common.CheckCode;
import com.jh.ui.common.CheckCodeGenerator;
import com.jh.ui.common.CommonMethod;
import com.jh.ui.listener.LoginListener;

/**
 * 注册号码窗体
 * @author Administrator
 *
 */
public class RegisterFrame extends JFrame implements ItemListener {

	private static final long serialVersionUID = -6900370409601641777L;
	
	// 省
	private String[] province = {"江西", "北京", "天津", "河北", "山东", "辽宁", "黑龙江", "吉林", "甘肃", "青海", "河南", "江苏",
								"湖北", "湖南", "浙江", "广东", "云南", "福建", "台湾", "海南", "山西", "四川", "陕西", "贵州", "安微"};
	// 市
	private String[] city = {"赣州", "南昌", "景德镇", "九江", "萍乡", "新余", "鹰潭", "宜春", "上饶", "吉安", "抚州"};
	// 区
	private String[] area = {"章贡区", "黄金开发区", "南康", "瑞金", "赣县", "信丰", "大余", "上犹", "崇义", "安远", "龙南", "定南",
								"全南", "兴国", "宁都", "于都", "会昌", "寻乌", "石城"};
	
	private JRadioButton boyBtn; // 单选按钮男
	private JRadioButton girlBtn; // 单选按钮女
	private JCheckBox ensureBox; // 复选按钮
	private JLabel submitLbl; // 提交注册标签
	private JComboBox<String> yearBox; // 年
	private JComboBox<String> monthBox; // 月
	private JComboBox<String> dayBox;// 日
	private JComboBox<String> provinceBox; // 省
	private JComboBox<String> cityBox; // 市
	private JComboBox<String> areaBox; // 区
	public static JTextField nicknameTxt; // 昵称输入框
	public static JPasswordField pwdTxt; // 密码输入框
	public static JPasswordField conpwdTxt; // 确认密码输入框
	public static JTextField mobileTxt; // 手机号输入框
	public static JTextField verificationTxt; // 验证码输入框
	public static JLabel randLbl; // 换一个验证码
	public static String checkCode; // 存储验证码
	public static int ageAcc = 0; // 存储年龄
	public static String genderAcc = "男"; // 存储性别
	public static String proviceAcc = "江西省"; // 存储省
	public static String cityAcc = "赣州市";
	public static String areaAcc = "章贡区";
	public static JLabel verLbl; // 放验证码的标签
	public static boolean submit = true; // 用来标识是否可以注册账号
	
	public RegisterFrame() {
		try {
			LookAndFeelInfo[] feel = UIManager.getInstalledLookAndFeels(); // 获取所有风格
			UIManager.setLookAndFeel(feel[1].getClassName()); // 设置Windows风格
		} catch (Exception e) {
			e.printStackTrace();
		}
		Constants.isRegisterFrameOpen = false;
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/qq_icon.png")));
		setTitle("注册账号");
		setSize(500, 550);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		initWidgets();
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				Constants.isRegisterFrameOpen = true;
			}
		});
		setVisible(true);
	}
	
	private void initWidgets() {
		JLabel nicknameLbl = new JLabel("昵称：");
		nicknameLbl.setBounds(60, 55, 50, 20);
		add(nicknameLbl);
		nicknameTxt = new JTextField();
		nicknameTxt.setBounds(120, 50, 300, 30);
		add(nicknameTxt);
		JLabel pwdLbl = new JLabel("密码：");
		pwdLbl.setBounds(60, 95, 50, 20);
		add(pwdLbl);
		pwdTxt = new JPasswordField();
		pwdTxt.setBounds(120, 90, 300, 30);
		add(pwdTxt);
		JLabel conpwdLbl = new JLabel("确认密码：");
		conpwdLbl.setBounds(35, 135, 80, 20);
		add(conpwdLbl);
		conpwdTxt = new JPasswordField();
		conpwdTxt.setBounds(120, 130, 300, 30);
		add(conpwdTxt);
		JLabel genderLbl = new JLabel("性别：");
		genderLbl.setBounds(60, 170, 50, 20);
		add(genderLbl);
		ButtonGroup group = new ButtonGroup(); // 把单选按钮组合
		boyBtn = new JRadioButton("男", true);
		boyBtn.setBounds(120, 170, 50, 20);
		boyBtn.setName("boy");
		boyBtn.addItemListener(this);
		girlBtn = new JRadioButton("女", false);
		girlBtn.setBounds(170, 170, 50, 20);
		girlBtn.setName("girl");
		girlBtn.addItemListener(this);
		group.add(boyBtn);
		group.add(girlBtn);
		add(boyBtn);
		add(girlBtn);
		JLabel birthdayLbl = new JLabel("生日：");
		birthdayLbl.setBounds(60, 205, 50, 20);
		add(birthdayLbl);
		JComboBox<String> calendarBox = new JComboBox<String>();
		calendarBox.setBounds(120, 200, 60, 30);
		calendarBox.addItem("公历");
		calendarBox.addItem("农历");
		add(calendarBox);
		yearBox = new JComboBox<String>();
		yearBox.setBounds(190, 200, 80, 30);
		for (int i = 2016; i >= 1897; i--) {
			yearBox.addItem(String.valueOf(i) + "年");
		}
		yearBox.addItemListener(this);
		add(yearBox);
		monthBox = new JComboBox<String>();
		monthBox.setBounds(280, 200, 60, 30);
		
		for (int i = 1; i <= 12; i++) {
			monthBox.addItem(String.valueOf(i) + "月");
		}
		monthBox.addItemListener(this);
		add(monthBox);
		dayBox = new JComboBox<String>();
		dayBox.setBounds(350, 200, 60, 30);
		
		for (int i = 1; i <= 31; i++) {
			dayBox.addItem(String.valueOf(i) + "日");
		}
		dayBox.addItemListener(this);
		add(dayBox);
		JLabel locationLbl = new JLabel("所在地：");
		locationLbl.setBounds(50, 245, 60, 20);
		add(locationLbl);
		provinceBox = new JComboBox<String>();
		provinceBox.setBounds(120, 240, 90, 30);
		for (int i = 0,size = province.length; i < size; i++) {
			provinceBox.addItem(province[i]);
		}
		provinceBox.addItemListener(this);
		add(provinceBox);
		cityBox = new JComboBox<String>();
		cityBox.setBounds(220, 240, 90, 30);
		for (int i = 0,size = city.length; i < size; i++) {
			cityBox.addItem(city[i]);
		}
		cityBox.addItemListener(this);
		add(cityBox);
		areaBox = new JComboBox<String>();
		areaBox.setBounds(320, 240, 90, 30);
		for (int i = 0,size = area.length; i < size; i++) {
			areaBox.addItem(area[i]);
		}
		areaBox.addItemListener(this);
		add(areaBox);
		JLabel mobileLbl = new JLabel("手机号码：");
		mobileLbl.setBounds(40, 285, 80, 20);
		add(mobileLbl);
		mobileTxt = new JTextField();
		mobileTxt.setBounds(120, 280, 300, 30);
		add(mobileTxt);
		JLabel verificationLbl = new JLabel("验证码：");
		verificationLbl.setBounds(50, 325, 80, 20);
		add(verificationLbl);
		verificationTxt = new JTextField();
		verificationTxt.setBounds(120, 320, 100, 30);
		add(verificationTxt);
		CheckCode cc = CheckCodeGenerator.getCheckCode();
		verLbl = new JLabel(new ImageIcon(cc.getBuffImg()));
		checkCode = cc.getCheckCode();
		verLbl.setBounds(250, 320, 50, 30);
		add(verLbl);
		JLabel exchangeLbl = new JLabel("换一张");
		exchangeLbl.setBounds(320, 325, 80, 20);
		exchangeLbl.setName("exchange");
		exchangeLbl.addMouseListener(new LoginListener(this, exchangeLbl));
		add(exchangeLbl);
		ensureBox = new JCheckBox("我已阅读并同意相关服务条款和隐私政策", true);
		ensureBox.setBounds(115, 360, 300, 20);
		ensureBox.addItemListener(this);
		ensureBox.setName("ensure");
		add(ensureBox);
		Icon submitIcon = CommonMethod.getImg(this, "submit_def.png");
		submitLbl = new JLabel(submitIcon);
		submitLbl.setBounds(115, 400, 304, 50);
		submitLbl.setName("submit");
		submitLbl.addMouseListener(new LoginListener(this, submitLbl));
		add(submitLbl);
	}

	/**
	 * 项目监听事件
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (ensureBox.isSelected()) { // 判断复选框是否被选中
			submitLbl.setIcon(CommonMethod.getImg(this, "submit_def.png"));
			submitLbl.setEnabled(true); // 标识注册图标可以点
			submit = true;
		} else {
			submitLbl.setIcon(CommonMethod.getImg(this, "submit_over.png"));
			submitLbl.setEnabled(false);
			submit = false;
		}
		if (boyBtn.isSelected()) { // 判断男是否被选中
			genderAcc = "男";
		} else if (girlBtn.isSelected()) {
			genderAcc = "女";
		}
		if (yearBox.getSelectedItem() != null && monthBox.getSelectedItem() != null && dayBox.getSelectedItem() != null) {
			String str = (String) yearBox.getSelectedItem(); // 获取到选中的项
			String str1 = str.substring(0,4); // 截取字符串的前4位
			int a = Integer.valueOf(str1);
			int age = 2016 - a;
			ageAcc = age; // 计算用户的年龄
		}
		if (provinceBox.getSelectedItem() != null && cityBox.getSelectedItem() != null && areaBox.getSelectedItem() != null) {
			String provice = (String) provinceBox.getSelectedItem();
			String city = (String) cityBox.getSelectedItem();
			String area = (String) areaBox.getSelectedItem();
			proviceAcc = provice;
			cityAcc = city;
			areaAcc = area;
		}
	}
}
