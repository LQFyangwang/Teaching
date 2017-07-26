package com.gs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.Pager;
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
				p.setDes(rs.getString("des"));
				p.setPrice(rs.getFloat("price"));
				products.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return products;
	}

	@Override
	public Pager<Product> queryByPager(int pageNo, int pageSize) {
		Pager<Product> pager = new Pager<Product>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		getConn();
		int top1 = pager.getPageSize();
		int top2 = (pager.getPageNo() - 1) * pager.getPageSize();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top1 + " * from t_product where id not in(select top " + top2 + " id from t_product)");
//			ps.setInt(1, pager.getPageSize());
//			ps.setInt(2, (pager.getPageNo() - 1) * pager.getPageSize());
			ResultSet rs = ps.executeQuery();
			List<Product> products = new ArrayList<Product>();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setDes(rs.getString("des"));
				p.setPrice(rs.getFloat("price"));
				products.add(p);
			}
			pager.setResult(products);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		return pager;
	}

	@Override
	public int count() {
		getConn();
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) from t_product");
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
