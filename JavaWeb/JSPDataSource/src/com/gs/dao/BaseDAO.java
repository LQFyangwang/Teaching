package com.gs.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BaseDAO {

	protected Connection conn;
	public void getConn() {
		try {
			Context context = new InitialContext(); // 获取jndi上下文
			Object obj = context.lookup("java:comp/env/datasource/mysql"); // 通过资源名称来获取资源
			if (obj instanceof DataSource) { // 对资源类型判断
				DataSource dataSource = (DataSource) obj; // 对资源做类型转换
				conn = dataSource.getConnection(); // DataSource从连接池中获取连接 
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
