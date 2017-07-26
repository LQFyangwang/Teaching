package com.gs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionTest1 {
	
	public static void main(String[] args) {
		User user = new User();
		user.setId("1002");
		user.setName("name");
		user.setPassword("password");
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=jdbc", "sa", "123456");
			PreparedStatement ps = conn.prepareStatement("select * from t_user"); // 会先对SQL语句进行执行前的准备工作，预编译
			PreparedStatement ps1 = conn.prepareStatement("insert into t_user(id, name, password) values('1005', '1005', '1005')");
			PreparedStatement ps2 = conn.prepareStatement("delete from t_user where id = '1003'");
			PreparedStatement ps3 = conn.prepareStatement("update t_user set password = ? where id = ?");
			ps1.executeUpdate();
			ps2.execute();
			ps3.setString(1, user.getPassword());
			ps3.setString(2, user.getId());
			int row = ps3.executeUpdate();
			System.out.println("更新的行数： " + row);
			ResultSet rs = ps.executeQuery(); // 告诉数据库服务器执行查询操作
			while (rs.next()) {
				System.out.println("id: " + rs.getString("id") + ", name: " + rs.getString("name") + ", password: " + rs.getString(3));
			}
			ps1.close();
			ps2.close();
			ps3.close();
			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
