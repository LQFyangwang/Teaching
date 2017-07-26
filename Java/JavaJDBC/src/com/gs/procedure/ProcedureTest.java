package com.gs.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class ProcedureTest {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=d_test", "sa", "123456");
		
		// my_pro
		
		CallableStatement cs = conn.prepareCall("exec my_pro");
		cs.execute();
		
		// my_pro1
		
		CallableStatement cs1 = conn.prepareCall("exec my_pro1 " + "'BMW', 'bb', 10");
		cs1.execute();
		
		// my_pro1
		
		CallableStatement cs2 = conn.prepareCall("exec my_pro1 ?, ?, ?");
		cs2.setString(1, "BMW");
		cs2.setString(2, "cc");
		cs2.setFloat(3, 100.0f);
		cs2.execute();
		
		// my_pro2
		
		CallableStatement cs3 = conn.prepareCall("exec my_pro2 ?, ?, ?, ?");
		cs3.setString(1, "AUDI");
		cs3.setString(2, "gggm");
		cs3.setFloat(3, 100.0f);
		cs3.registerOutParameter(4, Types.INTEGER);// jdk1.4
		cs3.execute();
		int id = cs3.getInt(4);// 通过输出参数的索引来获取输出参数的值
		System.out.println(id);
		
	}

}
