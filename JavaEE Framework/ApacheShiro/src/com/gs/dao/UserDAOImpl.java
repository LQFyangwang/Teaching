package com.gs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gs.bean.User;

public class UserDAOImpl extends AbstractBaseDAO implements UserDAO {

	@Override
	public List<User> queryAll() {
		getConn();
		return null;
	}

	@Override
	public User queryByEmailAndPwd(String email, String pwd) {
		getConn();
		User user = null;
		try {
		PreparedStatement ps = conn.prepareStatement("select * from users where username = ? and password = ?");
		ps.setString(1, email);
		ps.setString(2, pwd);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
		}
		} catch (SQLException e) {
			
		}
		return user;
	}
	
	

}
