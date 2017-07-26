package com.jh.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.jh.bean.Account;
import com.jh.bean.Message;
import com.jh.client.Client;
import com.jh.common.Constants;
import com.jh.common.DateUtil;
import com.jh.dao.AccountDAO;
import com.jh.dao.AccountDAOImpl;
import com.jh.ui.common.CommonMethod;
import com.jh.ui.common.LoginFrameCommon;
import com.jh.ui.common.UIUtil;
import com.jh.ui.listener.ChatFrameBtnListener;
import com.jh.ui.listener.ChatMouseListener;
import com.jh.ui.listener.WindowCloseListener;

public class ChatFrame extends JFrame {

	private static final long serialVersionUID = -4271690725650536173L;
	
	private Client client;
	private Account account;
	private Account toAccount;
	private JTextArea messageArea;
	private JTextArea inputArea;
	private AccountDAO accountDAO;
	
	public Account getAccount() {
		return account;
	}

	public Account getToAccount() {
		return toAccount;
	}

	public JTextArea getMessageArea() {
		return messageArea;
	}

	public JTextArea getInputArea() {
		return inputArea;
	}

	public ChatFrame(Client client, Account account, Account toAccount) {
		this.client = client;
		this.account = account;
		this.toAccount = toAccount;
		LoginFrameCommon.setStyle(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/" + toAccount.getHead() + ".png"))); // 设置任务栏图标
		initTop();
		initCenter();
		getContentPane().setBackground(new Color(245, 245, 220));
		setBounds(300, 100, 600, 525);
		addWindowListener(new WindowCloseListener(client, account));
	}
	
	/**
	 * 上面的部分
	 */
	public void initTop() {
		// 头像
		JButton headBtn = new JButton(CommonMethod.getImg(this, toAccount.getHead() + ".png"));
		headBtn.setBounds(10, 10, 50, 50);
		headBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		headBtn.setActionCommand("datum");
		headBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				accountDAO = new AccountDAOImpl();
				Account a = accountDAO.query(toAccount.getNumber());
				if (a != null) {
					if (Constants.isFriendMessageExtendsOpen) {
						new FriendMessageExtends(a);
					}
				}
			}
		});
		add(headBtn);
		// 昵称
		JLabel nicknameLbl = new JLabel(toAccount.getNickname());
		nicknameLbl.setBounds(70, 13, 60, 30);
		nicknameLbl.setFont(UIUtil.myFont(17));
		add(nicknameLbl);
		// 会员
		JLabel memberLbl = new JLabel(CommonMethod.getImg(this, "SVIP.png"));
		memberLbl.setBounds(130, 13, 60, 30);
		add(memberLbl);
		// 个性签名
		JLabel spaceLbl = new JLabel(CommonMethod.getImg(this, "space.png"));
		spaceLbl.setBounds(70, 43, 20, 20);
		add(spaceLbl);
		JLabel autographLbl = new JLabel(toAccount.getAutograph());
		autographLbl.setBounds(90, 43, 300, 20);
		add(autographLbl);
		// 上面部分
		JPanel topPanel = new JPanel();
		topPanel.setBounds(0, 65, 400, 40);
		topPanel.setLayout(new FlowLayout());
		topPanel.setBackground(new Color(0, 0, 0, 0));
		String[] name = {"voice", "video", "demonstrate", "file", "desktop", "discuss", "apply"};
		String[] toolTip = {"语音聊天", "视频通话", "远程演示", "文件传输", "远程控制", "创建讨论组", "应用"};
		for (int i = 1; i < 8; i++) {
			JLabel lbl = new JLabel(CommonMethod.getImg(this, "top_0" + i + ".png"));
			lbl.setName(name[i - 1]);
			lbl.setToolTipText(toolTip[i - 1]);
//			lbl.addMouseListener(new ChatMouseListener(lbl));
			topPanel.add(lbl);
		}
		add(topPanel);
		JLabel lineLbl = new JLabel();
		lineLbl.setBounds(0, 114, 459, 1);
		lineLbl.setBorder(BorderFactory.createLineBorder(new Color(105, 105, 105)));
		add(lineLbl);
		
		Icon minIcon = CommonMethod.getImg(this, "min_def.png");
		JLabel minLbl = new JLabel(minIcon);
		minLbl.setBounds(540, 0, 30, 30);
		minLbl.setToolTipText("最小化");
		minLbl.setName("min");
		minLbl.addMouseListener(new ChatMouseListener(this, minLbl, client));
		add(minLbl);
		Icon exitIcon = CommonMethod.getImg(this, "exit_def.png");
		JLabel exitLbl = new JLabel(exitIcon);
		exitLbl.setBounds(570, 0, 30, 30);
		exitLbl.setToolTipText("关闭");
		exitLbl.setName("dispose");
		exitLbl.addMouseListener(new ChatMouseListener(this, exitLbl, client));
		add(exitLbl);
	}
	
	/**
	 * 中间的部分
	 */
	private void initCenter() {
		JPanel messagePanel = new JPanel();
		messagePanel.setBounds(0, 115, 450, 250);
		messagePanel.setOpaque(false);
		messagePanel.setLayout(null);
		messageArea = new JTextArea();
		messageArea.setBorder(null);
		messageArea.setForeground(Color.RED);
		messageArea.setFont(new Font("微软雅黑", Font.ITALIC, 16));
		messageArea.setEditable(false); // 设置为不可编辑 setEnabled();设置整个JTextArea不可用
		messageArea.setBackground(new Color(245, 245, 220));
		JScrollPane scroll = new JScrollPane(messageArea);
		scroll.setBounds(9, 0, 445, 250);
		scroll.setBorder(null);
		scroll.setOpaque(false);
		messagePanel.add(scroll);
		add(messagePanel);
		
		JLabel lineLbl1 = new JLabel();
		lineLbl1.setBounds(0, 366, 459, 1);
		lineLbl1.setBorder(BorderFactory.createLineBorder(new Color(105, 105, 105)));
		add(lineLbl1);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(0, 360, 490, 35);
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.setBackground(new Color(0, 0, 0, 0));
		String[] name = {"font", "face", "magic", "shake", "voice1", "function", "img", "music", "screen"};
		String[] toolTip = {"字体选择工具栏", "选择表情", "魔棒", "抖一抖", "语音消息", "多功能辅助输入", "发送图片", "点歌", "屏幕截图"};
		for (int i = 1; i < 10; i++) {
			JLabel lbl = new JLabel(CommonMethod.getImg(this, "bottom_0" + i + ".png"));
			lbl.setName(name[i - 1]);
			lbl.setToolTipText(toolTip[i - 1]);
			lbl.addMouseListener(new ChatMouseListener(this, lbl, client));
			bottomPanel.add(lbl);
		}
		
		JLabel seekLbl = new JLabel("查看文件");
		seekLbl.setName("seek");
		seekLbl.addMouseListener(new ChatMouseListener(this, seekLbl, client));
		bottomPanel.add(seekLbl);
		
		JLabel openLbl = new JLabel("打开文件位置");
		openLbl.setName("open");
		openLbl.addMouseListener(new ChatMouseListener(this, openLbl, client));
		bottomPanel.add(openLbl);
		add(bottomPanel);
		
		inputArea = new JTextArea();
		inputArea.setBackground(new Color(245, 245, 220));
		inputArea.setBorder(null);
		JScrollPane inputScroll = new JScrollPane(inputArea);
		inputScroll.setBounds(10, 400, 450, 80);
		inputScroll.setOpaque(false);
		inputScroll.setBorder(null);
		add(inputScroll);
		
		JButton closeBtn = new JButton( CommonMethod.getImg(this, "close.png"));
		closeBtn.setMnemonic('c'); // 给按钮添加快捷键
		closeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeBtn.setBounds(308, 490, 68, 25);
		closeBtn.setActionCommand("close");
		closeBtn.addActionListener(new ChatFrameBtnListener(this, client));
		add(closeBtn);
		JButton sendBtn = new JButton(CommonMethod.getImg(this, "send.png"));
		sendBtn.setMnemonic('s');
		sendBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sendBtn.setBounds(388, 490, 62, 25);
		sendBtn.setActionCommand("send");
		sendBtn.addActionListener(new ChatFrameBtnListener(this, client));
		add(sendBtn);
		
		// 广告
		JLabel adLbl = new JLabel(CommonMethod.getImg(this, "ad.png"));
		adLbl.setBounds(459, 58, 141, 467);
		add(adLbl);
	}
	
	/**
	 * 更新消息
	 * @param message
	 */
	public void updateMessage(Message message) {
		if (message.getType() == Message.SEND_FILE_MSG) {
			int result = JOptionPane.showConfirmDialog(this, message.getFromAccount().getNickname() + "  给你发送了一个文件，是否接收", "接收文件", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {
				JOptionPane.showMessageDialog(this, "文件接收成功");
				Constants.fileName = message.getMessage();
				Constants.isFile = true;
			} else {
				JOptionPane.showMessageDialog(this, "文件接收失败");
				Constants.isFile = false;
			}
		} else if (message.getType() == Message.SHAKE_MSG) {
			String str = "对方给你发送了一个抖一抖";
			messageArea.setText(messageArea.getText() + "\n\t    " + str);
		} else {
			messageArea.setText(messageArea.getText() + "\n" + message.getFromAccount().getNickname() + "  " + DateUtil.getDateStr(DateUtil.getDate()) + "\n" + message.getMessage());
		}
	}
	
	public void updateShake() {
		ShakeThread st = new ShakeThread(this);
		new Thread(st).start();
	}
	
	class ShakeThread implements Runnable {

		private ChatFrame chatFrame;
		
		public ShakeThread(ChatFrame chatFrame) {
			this.chatFrame = chatFrame;
		}
		
		@Override
		public void run() {
			int x = chatFrame.getX();
			int y = chatFrame.getY();
			if (!chatFrame.isVisible()) {
				chatFrame.setVisible(true);
			}
			for (int i = 0; i < 3; i++) {
				try {
					chatFrame.setBounds(x + 5, y - 5, 600, 525);
					Thread.sleep(50);
					chatFrame.setBounds(x - 5, y + 5, 600, 525);
					Thread.sleep(50);
					chatFrame.setBounds(x + 5, y + 5, 600, 525);
					Thread.sleep(50);
					chatFrame.setBounds(x - 5, y - 5, 600, 525);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
		
	}
}
