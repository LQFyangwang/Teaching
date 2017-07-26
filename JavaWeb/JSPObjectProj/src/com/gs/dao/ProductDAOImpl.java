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
		try {
			List<Product> products = new ArrayList<Product>();
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
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Product queryById(int id) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_product where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Product product = new Product();
				product.setId(id);
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
				product.setDes(rs.getString("des"));
				return product;
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
