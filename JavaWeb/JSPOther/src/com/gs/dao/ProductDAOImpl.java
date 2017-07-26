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
				p.setImage(rs.getString("image"));
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
				product.setImage(rs.getString("image"));
				return product;
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public void update(Product product) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_product set name = ?, price = ?, des = ? where id = ?");
			ps.setString(1, product.getName());
			ps.setFloat(2, product.getPrice());
			ps.setString(3, product.getDes());
			ps.setInt(4, product.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
	}

	@Override
	public void add(Product product) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_product(name, price, des, image) values(?, ?, ?, ?)");
			ps.setString(1, product.getName());
			ps.setFloat(2, product.getPrice());
			ps.setString(3, product.getDes());
			ps.setString(4, product.getImage());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
	}

}
