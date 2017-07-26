package com.gs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.Product;
import com.gs.common.bean.Pager4EasyUI;

public class ProductDAOImpl extends BaseDAO implements ProductDAO {

	@Override
	public Product add(Product product) {
		getConn();
		String sql = "insert into t_product(id, title, price, des) values(uuid(), ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, product.getTitle());
			ps.setDouble(2, product.getPrice());
			ps.setString(3, product.getDes());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return product;
	}

	@Override
	public Pager4EasyUI<Product> queryAll(Pager4EasyUI<Product> pager) {
		getConn();
		String sql = "select * from t_product limit ?, ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pager.getBeginIndex());
			ps.setInt(2, pager.getPageSize());
			ResultSet rs = ps.executeQuery();
			List<Product> products = new ArrayList<Product>();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getString("id"));
				p.setTitle(rs.getString("title"));
				p.setPrice(rs.getDouble("price"));
				p.setDes(rs.getString("des"));
				products.add(p);
			}
			pager.setRows(products);
			pager.setTotal(count());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public Product queryById(String id) {
		getConn();
		String sql = "select * from t_product";
		Product product = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product = new Product();
				product.setId(rs.getString("id"));
				product.setTitle(rs.getString("title"));
				product.setPrice(rs.getDouble("price"));
				product.setDes(rs.getString("des"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return product;
	}

	@Override
	public void update(Product product) {
		getConn();
		String sql = "update t_product set title = ?, price = ?, des = ? where id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, product.getTitle());
			ps.setDouble(2, product.getPrice());
			ps.setString(3, product.getDes());
			ps.setString(4, product.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public void deleteByIds(String[] ids) {
		getConn();
//		try {
//			Statement stmt = conn.createStatement();
//			for (String id : ids) {
//				String sql = "delete from t_product where id ='" + id + "'";
//				stmt.addBatch(sql);
//			}
//			stmt.executeBatch();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		String sql = "delete from t_product where id = '[id]'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			for (String id : ids) {
				ps.addBatch(sql.replace("[id]", id));
			}
			ps.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}
	
	@Override
	public int count() {
		getConn();
		String sql = "select count(id) from t_product";
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return count;
	}

}
