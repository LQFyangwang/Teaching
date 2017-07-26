package com.gs.listener;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ListenerTest extends Frame {
	
	public ListenerTest() {
		setSize(400, 400);
		setLocation(200, 200);
		
		setLayout(new FlowLayout());
		
		Button btn = new Button("按钮");
		btn.addActionListener(new ActionListener() {
			// 点击按钮
			@Override
			public void actionPerformed(ActionEvent e) { 
				Object obj = e.getSource(); // 通过事件对象获取事件源
				if (obj instanceof Button) { // 判断事件源是否为Button类的实例
					Button btn = (Button) obj; // 如果是Button类的实例，则强制转化成Button类
					System.out.println(btn.getLabel()); // 通过事件源获取此Button的信息
				}
			}
		});
		add(btn);
		
		//******************* 焦点事件 **************************
		TextField  field1 = new TextField("焦点事件");
		field1.addFocusListener(new FocusListener() {
			// 失去焦点
			@Override
			public void focusLost(FocusEvent e) {
				System.out.println(FocusEvent.FOCUS_FIRST); // 1004
				System.out.println(FocusEvent.FOCUS_LAST); // 1005
				System.out.println("文本框 失去焦点");
			}
			// 获得焦点
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("文本框 获得焦点");
			}
		});
		//********************* 动作事件 （点击按钮，文本框按回车）***************************
		TextField  field2 = new TextField("动作事件");
		field2.addActionListener(new ActionListener() {
			// 文本框的action事件是在按回车键时触发
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("文本框Action");
			}
		});
		//******************** 文本事件 ************************************
		TextField  field3 = new TextField("文本事件");
		field3.addTextListener(new TextListener() {
			// 文本组件内的文本被修改后就会触发该事件
			@Override
			public void textValueChanged(TextEvent e) {
				TextField f = (TextField) e.getSource();
				System.out.println(f.getText());
			}
		});
		//******************* 键盘按键事件 ***************************
		TextField  field4 = new TextField("键盘按键事件");
		field4.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("teyped key");
			}
			// 当在键盘上按下某键
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode()); // 返回按鍵所对应的assci码
				System.out.println(e.getKeyChar()); // 返回按键所对应的字符，比如按a，返回a
				System.out.println(e.getKeyLocation()); // 同一个键盘上可能有重复的键，返回这个键来源于哪个位置
				System.out.println(e.getKeyCode() == KeyEvent.VK_RIGHT); // KeyEvent类中有键盘上对应的常量值
				System.out.println("pressed key");
			}
			// 当在键盘上松开按键
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("released key");
			}
		});
		// ************************* 鼠标事件 *********************
		TextField  field5 = new TextField("鼠标事件");
		field5.addMouseListener(new MouseListener() {
			// 鼠标按下后松开
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("mouse release");
			}
			// 鼠标点击 
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("x: " + e.getX() + ", y: " + e.getY());
				// e.getClickCount(); //返回按键的次数
				// e.getPoint(); // 返回对x,y坐标封装类的对象
				System.out.println("mouse press");
			}
			// 鼠标离开
			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println("mouse exited");
			}
			// 鼠标进入
			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("mouse enter");
			}
			// 鼠标点击 
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("mouse clicked");
			}
		});
		// ************************* 鼠标状态事件（移动和拖动） *********************
		TextField  field6 = new TextField("鼠标状态事件");
		field6.addMouseMotionListener(new MouseMotionListener() {
			// 监听当鼠标移动
			@Override
			public void mouseMoved(MouseEvent e) {
				System.out.println("mouse moved...");
			}
			// 监听鼠标拖动
			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.println("mouse dragged...");
				
			}
		});
		// ************************* 鼠标滚轮事件 *********************
		TextField  field7 = new TextField("鼠标滚轮事件");
		field7.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				System.out.println("mouse wheel moved...");
			}
		});
		add(field1);
		add(field2);
		add(field3);
		add(field4);
		add(field5);
		add(field6);
		add(field7);
		
		setVisible(true);
		// ********************** 窗体事件 ***************************
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println(e.getSource());
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		new ListenerTest();
	}
	
}
