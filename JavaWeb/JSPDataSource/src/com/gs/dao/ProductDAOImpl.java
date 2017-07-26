package com.gs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.Product;

public class ProductDAOImpl extends BaseDAO implements ProductDAO {

	@Override
	public List<Product> queryAll() {
		getConn();
		List<Product> products = new ArrayList<Product>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_product");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getFloat("price"));
				p.setDes(rs.getString("des"));
				products.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return products;
	}

}
