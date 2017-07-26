package com.gs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolePermissionDAOImpl extends AbstractBaseDAO implements RolePermissionDAO {

	@Override
	public List<String> queryAllPermissionByRoleName(String roleName) {
		getConn();
		List<String> permissions = new ArrayList<String>();
		try {
			PreparedStatement ps = conn.prepareStatement("select permission from roles_permissions where role_name = ?");
			ps.setString(1, roleName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				permissions.add(rs.getString(1));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return permissions;
	}

}
