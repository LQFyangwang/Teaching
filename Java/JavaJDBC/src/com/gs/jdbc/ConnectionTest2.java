package com.gs.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest2 {
	
	public static void main(String[] args) {
		User user = new User();
		user.setId("1002");
		user.setName("10001");
		user.setPassword("10001");
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=jdbc", "sa", "123456");
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			stmt.execute("insert into t_user(id, name, password) values('1005', '1005', '1005')");
			ResultSet rs = stmt.executeQuery("select * from t_user");
			while (rs.next()) {
				System.out.println("id: " + rs.getString("id") + ", name: " + rs.getString("name") + ", password: " + rs.getString(3));
			}
			DatabaseMetaData dmd = conn.getMetaData();
			String driverName = dmd.getDriverName();
			String userName = dmd.getUserName();
			String url = dmd.getURL();
			ResultSet tables = dmd.getTablePrivileges("jdbc", null, null);
			System.out.println("drivername: " + driverName + ", username: " + userName + ", url: " + url);
			if (tables.next()) {
				System.out.println(tables.getString(3)); // 表名称
			}
			ResultSetMetaData rsmd = rs.getMetaData();
			int totalColumns = rsmd.getColumnCount();
			String tableName = rsmd.getTableName(1);
			String columnName = rsmd.getColumnName(1);
			String columnType = rsmd.getColumnTypeName(1);
			System.out.println(tableName + "共" + totalColumns + "列, " + "第一列：" + columnName + ", " + columnType);
			rs.close();
			stmt.close();
			conn.rollback();
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
