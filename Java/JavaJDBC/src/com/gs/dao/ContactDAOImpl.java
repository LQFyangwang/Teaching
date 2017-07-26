package com.gs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.Contact;

public class ContactDAOImpl extends BaseDAO implements ContactDAO {

	@Override
	public Contact queryById(int id) {
		Connection conn = getConnection();
		Contact contact = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_contact where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				contact = new Contact();
				contact.setId(id);
				contact.setName(rs.getString("name"));
				contact.setPhone(rs.getString("phone"));
				contact.setAddress(rs.getString("address"));
				contact.setCompany(rs.getString("company"));
				contact.setCreateTime(rs.getDate("create_time"));
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contact;
	}

	@Override
	public List<Contact> queryAll() {
		Connection conn = getConnection();
		List<Contact> contacts = new ArrayList<Contact>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_contact");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Contact contact = new Contact();
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setPhone(rs.getString("phone"));
				contact.setAddress(rs.getString("address"));
				contact.setCompany(rs.getString("company"));
				contact.setCreateTime(rs.getDate("create_time"));
				contacts.add(contact);
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contacts;
	}

	@Override
	public Contact add(Contact contact) {
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_contact(name, phone, address, company, create_time) values(?, ?, ?, ?, ?)");
			ps.setString(1, contact.getName());
			ps.setString(2, contact.getPhone());
			ps.setString(3, contact.getAddress());
			ps.setString(4, contact.getCompany());
			ps.setDate(5, contact.getCreateTime());
			ps.executeUpdate();
			ps.close();
			conn.close();
			return contact;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(int id) {
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("delete from t_contact where id = ?");
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Contact contact) {
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_contact set name=?, phone=?, address=?, company=?,create_time=? where id = ?");
			ps.setString(1, contact.getName());
			ps.setString(2, contact.getPhone());
			ps.setString(3, contact.getAddress());
			ps.setString(4, contact.getCompany());
			ps.setDate(5, contact.getCreateTime());
			ps.setInt(6, contact.getId());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
