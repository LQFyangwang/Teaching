package com.gs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.ProductType;

public class ProductTypeDAOImpl extends BaseDAO implements ProductTypeDAO {

	@Override
	public List<ProductType> queryAll() {
		String sql = "select * from t_product_type";
		getConn();
		List<ProductType> productTypes = new ArrayList<ProductType>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductType p = new ProductType();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				productTypes.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return productTypes;
	}

}
