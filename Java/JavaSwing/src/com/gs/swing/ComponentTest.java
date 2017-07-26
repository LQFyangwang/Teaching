package com.gs.swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ComponentTest extends JFrame {
	
	public ComponentTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		setSize(400, 400);
		setTitle("组件测试");
		setLocationRelativeTo(null);
		
		setLayout(new FlowLayout()); // setLayout()直接给内容面板设置成FlowLayout， 而不是该 JFrame窗体
		System.out.println(getLayout().getClass().getName()); // 顶层窗口默认使用边框布局
		
		// 获取顶层容器的内容面板， 所有的的Swing组件都是放在该内容面板上，每个swing的JFrame默认设置了一个JPanel内容面板
		// 如果没有调用设置内容面板的方法，则顶层容器默认提供了一个内容面板，该内容面板是javax.swing.JPanel
		Container pane = getContentPane(); 
		Container rootPane = getRootPane();
//		 setContentPane(new ContentPane()); // 设置自定义的内容面板，此内容面板继承自JPanel类
		
		System.out.println(pane.getClass().getName());// javax.swing.JPanel
		
		JButton btn = new JButton("点我！");
		btn.setSize(100, 100);
		// btn.setText("aaa");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("按钮点击");
//				JDialog dialog = new JDialog(ComponentTest.this);
//				dialog.setSize(200, 200);
//				dialog.setLocationRelativeTo(null);
//				dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//				dialog.setVisible(true);
				//************************* 对话框的使用 *****************************
				JOptionPane.showMessageDialog(ComponentTest.this, "我是对话框"); // 显示消息对话框，第一个参数是指父容器，第二个参数是消息字符串
				JOptionPane.showOptionDialog(ComponentTest.this, "我是选项对话框", "标题", 
						JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null); // 父容器，消息，标题，对话框中的按钮，消息的类型
				int result = JOptionPane.showConfirmDialog(ComponentTest.this, "确认吗？"); // 返回值 为0表示点击确定，1表示否，2表示取消
				System.out.println("showConfirmDialog result: " + result);
				int result1 = JOptionPane.showConfirmDialog(ComponentTest.this, "确认吗？", "确认操作", JOptionPane.OK_CANCEL_OPTION);
				System.out.println("showConfirmDialog reuslt: " + result1);
			}
			
		});
		pane.add(btn);
		JTextField fld = new JTextField("默认文本", 20);
		fld.setForeground(Color.RED);
		fld.setFont(new Font("黑体", Font.BOLD, 15));
		fld.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyChar());
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		add(fld);
		JPasswordField pf = new JPasswordField(10);
		add(pf);
		
		JCheckBox box1 = new JCheckBox("1", false);
		box1.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("状态改变"); // 鼠标移动该选项和移出该选项时触发
			}
		});
		JCheckBox box2 = new JCheckBox("2", true);
		ButtonGroup group1 = new ButtonGroup();
		group1.add(box1);
		group1.add(box2); // 一旦使用ButtonGroup把JCheckBox归为一组，则类似于单选按钮的功能
		add(box1);
		add(box2);
		
		JRadioButton btn1 = new JRadioButton("1", false);
		JRadioButton btn2 = new JRadioButton("2", false);
		ButtonGroup group = new ButtonGroup(); // ButtonGroup不是组件，是一个逻辑分组
		group.add(btn1);
		group.add(btn2);
		add(btn1);
		add(btn2);
		
		JToggleButton tbtn = new JToggleButton("开", false);
		tbtn.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				JToggleButton btn = (JToggleButton) e.getSource();
				String text = btn.getText();
				System.out.println(text);
				if (text.equals("开")) { // 如果该状态按钮原先是开，则设置为关
					btn.setText("关");
				} else {
					btn.setText("开");
				}
			}
 		});
		add(tbtn);
		
		JComboBox cb1 = new JComboBox(new String[]{"ABC", "BCD", "CDE"});
		cb1.addItem("DEF"); // 添加项
		cb1.removeItemAt(1); // 删除指定索引的项
		JComboBox<String> cb2 = new JComboBox<String>(new String[]{"abc", "bcd", "cde"}); // 以后再说
		add(cb1);
		add(cb2);
		
		JButton fileBtn = new JButton("选择文件");
		fileBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
				int result = c.showOpenDialog(ComponentTest.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = c.getSelectedFile(); // 获取到选择的文件
					System.out.println(file.getPath() + ", " + file.getName());
					System.out.println("成功打开文件");
				} else if (result == JFileChooser.CANCEL_OPTION) {
					System.out.println("取消文件选择");
				}
			}
		});
		add(fileBtn);
		
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("文件");
		JMenuItem item1 = new JMenuItem("打开");
		JMenuItem item2 = new JMenuItem("新建");
		menu.add(item2);
		menu.addSeparator();
		menu.add(item1);
		item1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("mouse enter item1");
				JPopupMenu subItem1 = new JPopupMenu("AA");
				subItem1.setVisible(true);
			}
			
		});
		bar.add(menu);
		setJMenuBar(bar);
		
		// pack(); // preferred size
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 设置默认关闭操作的方式
		UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel"); // 设置窗体和组件的外观
		setVisible(true);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		new ComponentTest();
	}

}
