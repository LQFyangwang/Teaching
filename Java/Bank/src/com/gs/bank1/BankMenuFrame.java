package com.gs.bank1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class BankMenuFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 95449655659233103L;
	
	public static final String[] btnNames = {"开户", "存款", "查询", "取款", "转账", "退出"};
	public static final String[] btnActions = {"openPanel", "savePanel", "query", "getPanel", "transferPanel", "exit"};
	
	private MyBank myBank;
	
	private BankOpenPanel openPanel;
	private BankSavePanel savePanel;

	public BankMenuFrame(Account currentAcount) {
		myBank = new MyBank();
		myBank.setCurrentAccount(currentAcount);
		setTitle("银行");
		setSize(400, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		initWidgets();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void initWidgets() {
		JPanel panel = new JPanel(new GridLayout(btnNames.length, 1));
		for (int i = 0, len = btnNames.length; i < len; i++) {
			JButton btn = new JButton(btnNames[i]);
			btn.setActionCommand(btnActions[i]);
			btn.addActionListener(this);
			panel.add(btn);
		}
		add(panel, BorderLayout.WEST);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		// 如果用户点出了开户操作，则把开户面板显示到中部，当点出确认后，则需要从该 面板中获取用户输入的数据，直接把该数据组装成Account对象，
		// 再调用BankFile.save(Account account)追加到数据文件中
		if (action.equals("openPanel")) {
			openPanel = new BankOpenPanel(myBank);
			if (savePanel != null) {
				remove(savePanel);
				savePanel = null;
			}
			add(openPanel, BorderLayout.CENTER);
			// ****** 特别注意：如果已经将某个组件添加到显示的容器中，则必须在此容器上调用 validate，以显示新的组件。
			// 如果添加多个组件，那么可以在添加所有组件之后，通过只调用一次 validate 来提高效率。 
			validate(); 
			// 当点击存款操作，则需要显示存款 面板，输入金额后，点击存款，此时需要把当前账号的余额变更，并且从整个List列表中把此账号的信息更新，
			// 更新完毕后把整个List列表重新写入到 文件 中（覆盖）
		} else if (action.equals("savePanel")) {
			savePanel = new BankSavePanel(myBank);
			if (openPanel != null) {
				remove(openPanel);
				openPanel = null;
			}
			add(savePanel);
			validate();
			// 直接返回当前已经登录的账户的信息
		} else if (action.equals("query")) {
			JOptionPane.showMessageDialog(this.getParent(), myBank.getCurrentAccount(), "账号信息", JOptionPane.INFORMATION_MESSAGE);
		} else if (action.equals("getPanel")) {

		} else if (action.equals("transferPanel")) {

		} else if (action.equals("exit")) {
			System.exit(0);
		}
	}
	
	
}
