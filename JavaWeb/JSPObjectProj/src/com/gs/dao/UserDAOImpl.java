package com.gs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gs.bean.User;

public class UserDAOImpl extends BaseDAO implements UserDAO {

	@Override
	public User queryByNamePwd(String name, String pwd) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_user where name = ? and pwd = ?");
			ps.setString(1, name);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPwd(rs.getString("pwd"));
				user.setAge(rs.getInt("age"));
				return user;
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
