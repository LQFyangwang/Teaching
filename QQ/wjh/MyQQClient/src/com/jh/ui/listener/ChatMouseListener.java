package com.jh.ui.listener;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import com.jh.bean.Account;
import com.jh.bean.Message;
import com.jh.client.Client;
import com.jh.common.Constants;
import com.jh.common.DateUtil;
import com.jh.ui.ChatFrame;
import com.jh.ui.common.CommonMethod;

/**
 * 聊天界面的一下JLabel的点击事件
 * @author Administrator
 *
 */
public class ChatMouseListener extends MouseAdapter {
	
	private JLabel label;
	private ChatFrame chatFrame;
	private Client client;
	private Account account;
	private Account toAccount;
	
	public ChatMouseListener(ChatFrame chatFrame, JLabel label, Client client) {
		this.label = label;
		this.chatFrame = chatFrame;
		this.client = client;
		this.account = chatFrame.getAccount();
		this.toAccount = chatFrame.getToAccount();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String name = CommonMethod.getLabelName(e);
		if (name.equals("voice")) { // top_发起通话语音
			
		} else if (name.equals("video")) { // top_发起视频通话
			
		} else if (name.equals("demonstrate")) { // top_远程演示
			
		} else if (name.equals("file")) { // top_发送文件
			
		} else if (name.equals("desktop")) { // top_远程桌面
			
		} else if (name.equals("discuss")) { // top_创建讨论组
			
		} else if (name.equals("apply")) { // top_应用

		} else if (name.equals("font")) { // bottom_字体
			
		} else if (name.equals("face")) { // bottom_表情
			
		} else if (name.equals("magic")) { // bottom_魔棒
			
		} else if (name.equals("shake")) { // bottom_抖一抖
			Message m = new Message(Message.SHAKE_MSG, account, toAccount, DateUtil.getDate(), account.getNumber() + "发送了抖一抖");
			client.sendMessage(m);
		} else if (name.equals("voice1")) { // bottom_语音消息
			setPopup("发送语音消息", "发送视频留言");
		} else if (name.equals("function")) { // bottom_多功能面板
			setPopup("手写输入", "语音识别");
		} else if (name.equals("img")) { // bottom_图片
			JFileChooser jfc = new JFileChooser();
//			FileFilter ff = new FileNameExtensionFilter("图像文件(JPG/GIF)", "JPG", "JPEG", "GIF"); // 设置文件过滤器
//			jfc.setFileFilter(ff);
			int result = jfc.showOpenDialog(chatFrame);
			if (result == JFileChooser.APPROVE_OPTION) {
				File file = jfc.getSelectedFile(); // 获取到选择的文件
				Message m = new Message(Message.SEND_FILE_MSG, account, toAccount, DateUtil.getDate(), file.getName()); // 发送一条发送文件的消息
				try {
					FileInputStream fis = new FileInputStream(file); // 去读这个选中的文件
					byte[] bytes = new byte[(int) file.length()]; // 定义一个字节数组，长度为文件的大小
					fis.read(bytes); // 把文件读进这个字节数据里面
					m.setBytes(bytes); // 存放到Message类里
					fis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				client.sendMessage(m);
			}
			
		} else if (name.equals("music")) { // bottom_点歌
			try {
				Runtime.getRuntime().exec("D:/软件安装/kugou/KGMusic/kugou"); // 启动酷狗
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (name.equals("screen")) { // bottom_截图
			JPopupMenu menu = new JPopupMenu();
			JMenuItem item1 = new JMenuItem("新建截图");
			item1.setActionCommand("news");
			item1.addActionListener(new CutActionListener());
			menu.add(item1);
			JMenuItem item2 = new JMenuItem("查看截图");
			item2.setActionCommand("seek");
			item2.addActionListener(new CutActionListener());
			menu.add(item2);
			label.add(menu);
			menu.show(label, 20, 20);
		} else if (name.equals("min")) { // 最小化
			chatFrame.setExtendedState(JFrame.ICONIFIED);
		} else if (name.equals("dispose")) { // 关闭
			chatFrame.setVisible(false);
			JTextArea area = chatFrame.getInputArea();
			String value = area.getText();
			if (!value.equals("") || value != null) {
				area.setText("");
			}
		} else if (name.equals("seek")) { // 查看文件
			if (Constants.isFile) {
				try {
					Desktop.getDesktop().open(new File("e:/workspace/MyQQClient/src/images/img/" + Constants.fileName));  
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else if (name.equals("open")) { // 打开文件位置
			try {
				Desktop.getDesktop().open(new File("e:/workspace/MyQQClient/src/images/img/"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		String name = CommonMethod.getLabelName(e);
		if (name.equals("min")) { // 最小化
			label.setIcon(CommonMethod.getImg(chatFrame, "min_over.png"));
		} else if (name.equals("dispose")) { // 关闭
			label.setIcon(CommonMethod.getImg(chatFrame, "exit_over.png"));
		} else if (name.equals("seek")) { // 查看文件
			if (Constants.isFile) {
				label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				label.setForeground(Color.RED);
			}
		} else if (name.equals("open")) { // 打开文件位置
			label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			label.setForeground(Color.RED);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		String name = CommonMethod.getLabelName(e);
		if (name.equals("min")) { // 最小化
			label.setIcon(CommonMethod.getImg(chatFrame, "min_def.png"));
		} else if (name.equals("dispose")) { // 关闭
			label.setIcon(CommonMethod.getImg(chatFrame, "exit_def.png"));
		} else if (name.equals("seek")) { // 查看文件
			label.setCursor(Cursor.getDefaultCursor());
			label.setForeground(Color.BLACK);
		} else if (name.equals("open")) { // 打开文件位置
			label.setCursor(Cursor.getDefaultCursor());
			label.setForeground(Color.BLACK);
		}
	}

	private void setPopup(String str, String str1) {
		JPopupMenu menu = new JPopupMenu();
		JMenuItem item1 = new JMenuItem(str);
		menu.add(item1);
		JMenuItem item2 = new JMenuItem(str1);
		menu.add(item2);
		label.add(menu);
		menu.show(label, 20, 20);
	}
}
