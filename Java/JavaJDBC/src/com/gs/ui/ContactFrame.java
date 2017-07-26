package com.gs.ui;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import com.gs.bean.Contact;
import com.gs.dao.ContactDAO;
import com.gs.dao.ContactDAOImpl;

public class ContactFrame extends JFrame {

	private static final long serialVersionUID = -8424998736637348448L;

	private ContactDAO contactDAO;
	
	public ContactFrame() {
		contactDAO = new ContactDAOImpl();
		setTitle("联系人");
		setSize(600, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		initWidgets();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void initWidgets() {
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("编号");
		columnNames.add("姓名");
		columnNames.add("手机号");
		columnNames.add("地址");
		columnNames.add("公司名");
		columnNames.add("创建时间");
		JTable t = new JTable(convert(contactDAO.queryAll()), columnNames);
		add(t, BorderLayout.CENTER);
	}
	
	private Vector<Vector<String>> convert(List<Contact> contacts) {
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		for (Contact c : contacts) {
			Vector<String> v = new Vector<String>();
			v.add(String.valueOf(c.getId()));
			v.add(c.getName());
			v.add(c.getPhone());
			v.add(c.getAddress());
			v.add(c.getCompany());
			v.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getCreateTime()));
			vector.add(v);
		}
		return vector;
	}

}
