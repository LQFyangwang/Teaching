package com.gs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.Product;
import com.gs.common.DateUtil;
import com.gs.common.bean.Pager4EasyUI;

public class ProductDAOImpl extends BaseDAO implements ProductDAO{

	@Override
	public List<Product> queryAll() {
		String sql = "select p.*, pt.name as pt_name from t_product p, t_product_type pt where p.type = pt.id";
		getConn();
		List<Product> products = new ArrayList<Product>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getLong("id"));
				p.setTitle(rs.getString("title"));
				p.setPrice(rs.getDouble("price"));
				p.setDes(rs.getString("des"));
				p.setType(rs.getInt("type"));
				p.setTypeName(rs.getString("pt_name"));
				if (p.getPdate() != null) {
					p.setpDateStr(DateUtil.getDateStr(p.getPdate()));
				}
				p.setEnterDate(rs.getDate("enterdate"));
				if (p.getEnterDate() != null) {
					p.setEnterDateStr(DateUtil.getDatetimeStr(p.getEnterDate()));
				}
				products.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return products;
	}

	@Override
	public int countAll() {
		String sql = "select count(id) from t_product";
		getConn();
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
	
	@Override
	public Product add(Product product) {
		String sql = "insert into t_product(title, price, des, type) values(?, ?, ?, ?)";
		getConn();
		Product p = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, product.getTitle());
			ps.setDouble(2, product.getPrice());
			ps.setString(3, product.getDes());
			ps.setInt(4, product.getType());
			ps.execute();
			p = product;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return p;
	}
	
	@Override
	public Product edit(Product product) {
		String sql = "update t_product set title = ?, price = ?, des = ?, type = ? where id = ?";
		getConn();
		Product p = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, product.getTitle());
			ps.setDouble(2, product.getPrice());
			ps.setString(3, product.getDes());
			ps.setInt(4, product.getType());
			ps.setLong(5, product.getId());
			ps.execute();
			p = product;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return p;
	}
	
	@Override
	public void delete(long id) {
		String sql = "delete from t_product where id = ?";
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}
	
	@Override
	public Pager4EasyUI<Product> queryByPager(Pager4EasyUI<Product> pager) {
		String sql = "select p.*, pt.name as pt_name from t_product p, t_product_type pt where p.type = pt.id limit ?, ?";
		getConn();
		List<Product> products = new ArrayList<Product>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pager.getStartIndex());
			ps.setInt(2, pager.getPageSize());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getLong("id"));
				p.setTitle(rs.getString("title"));
				p.setPrice(rs.getDouble("price"));
				p.setDes(rs.getString("des"));
				p.setType(rs.getInt("type"));
				p.setTypeName(rs.getString("pt_name"));
				p.setPdate(rs.getDate("pdate"));
				if (p.getPdate() != null) {
					p.setpDateStr(DateUtil.getDateStr(p.getPdate()));
				}
				p.setEnterDate(rs.getDate("enterdate"));
				if (p.getEnterDate() != null) {
					p.setEnterDateStr(DateUtil.getDatetimeStr(p.getEnterDate()));
				}
				products.add(p);
			}
			pager.setRows(products);
			pager.setTotal(countAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

}
