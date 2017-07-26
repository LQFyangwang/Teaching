package com.gs.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gs.dao.BaseDAO;

public class DAOTest extends BaseDAO {
	
	public void test() {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_user");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("id") + rs.getString("name") + rs.getString("email"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}
	
	public static void main(String[] args) {
		DAOTest test = new DAOTest();
		test.test();
	}

}
