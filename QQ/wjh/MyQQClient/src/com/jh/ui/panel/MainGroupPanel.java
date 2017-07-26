package com.jh.ui.panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.jh.bean.Account;

public class MainGroupPanel extends JPanel {

	private static final long serialVersionUID = -4523251512810571833L;

	public MainGroupPanel(JFrame frame, List<Account> accounts) {
		setBounds(0, 0, 260, 360);
		setOpaque(false);
		setBorder(null);
		JList<Account> fList = new JList<Account>();
		fList.setFixedCellHeight(80);
		fList.setFixedCellWidth(280);
		fList.setVisibleRowCount(4);
		fList.setBorder(null);
		DefaultListModel<Account> listModel = new DefaultListModel<Account>();
		if (accounts != null) {
			for (Account a : accounts) {
				listModel.addElement(a);
			}
		}
		for (int i = 0; i < 5; i++) {
			Account a = new Account();
			a.setNickname("AAAA" + i);
			a.setHead("head");
			a.setNumber("12413" + i);
			a.setStatus("在线");
			a.setAutograph("今天天气好好");
			listModel.addElement(a);
		}
		Account a1 = new Account();
		a1.setNickname("BBBB");
		a1.setHead("head1");
		a1.setNumber("12413");
		a1.setStatus("离线");
		a1.setAutograph("今天心情很糙");
		listModel.addElement(a1);
		fList.setModel(listModel);
		fList.setCellRenderer(new GroupListCellRenderer(frame));
		fList.setOpaque(false);
		fList.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {}

			@Override
			public void mouseMoved(MouseEvent e) {
				@SuppressWarnings("rawtypes")
				JList list = (JList) e.getSource(); // 获取当前鼠标的位置
				int index = list.locationToIndex(e.getPoint()); // locationToIndex(Point)
				list.setSelectedIndex(index); // 把正好在鼠标位置的那个项目的索引设置为被选中，一旦设置为被选中，则CellRenderer里的isSelected则为true
			}
		});
		JScrollPane scroll = new JScrollPane(fList);
		scroll.setBounds(10, 0, 260, 360);
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);
		scroll.setBorder(null);
		add(scroll);
	}
}
