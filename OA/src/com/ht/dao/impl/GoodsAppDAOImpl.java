package com.ht.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.GoodsApp;
import com.ht.bean.info.GoodsAppInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.GoodsAppDAO;

public class GoodsAppDAOImpl extends AbstractBaseDAO<GoodsApp> implements GoodsAppDAO {

	private Pager4EasyUI<GoodsAppInfo> setPager(Pager4EasyUI<GoodsAppInfo> pager, String sql) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list(); // 每一个Object[]存放的是每一行记录的所有字段
		List<GoodsAppInfo> app = new ArrayList<GoodsAppInfo>();
		for(Object[] objs : list){
			GoodsAppInfo info = new GoodsAppInfo();
			info.setGoodsAppId((String) objs[0]);
			info.setAppDay((Date) objs[1]);
			info.setQuantity((int) objs[2]);
			info.setDes((String) objs[3]);
			info.setStatus((int) objs[4]);
			info.setAppStatus((int) objs[5]);
			info.setGoodsName((String) objs[6]);
			info.setGoodsTypeId((String) objs[7]);
			info.setPrice((double) objs[8]);
			info.setEmpId((String) objs[9]);
			info.setEmpName((String) objs[10]);
			app.add(info);
		}
		pager.setRows(app);
		return pager;
	}
	
	@Override
	public Pager4EasyUI<GoodsAppInfo> queryByPager(Pager4EasyUI<GoodsAppInfo> pager) {
		String sql = "select g.goodsappid,g.appday,g.quantity,g.des,g.status,g.appstatus,g.goodsname,g.goodstypeid,g.price,e.empid, e.name from t_goodsapp g, t_emp e where g.empid = e.empid";
		return setPager(pager, sql);
	}

	@Override
	public Pager4EasyUI<GoodsAppInfo> queryBySelf(Pager4EasyUI<GoodsAppInfo> pager, Serializable empId) {
		String sql = "select g.goodsappid,g.appday,g.quantity,g.des,g.status,g.appstatus,g.goodsname,g.goodstypeid,g.price, e.empid,e.name from t_goodsapp g, t_emp e where g.empid = e.empid and g.empid = '" + empId + "'";
		return setPager(pager, sql);
	}
	
	@Override
	public void updateAppStatus(String beanName, String levelName, String idName, int secondLevel, String id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "update " + beanName + " set " + levelName + " = :secondLevel where " + idName + " = :id";
		Query query = session.createQuery(hql);
		query.setParameter("secondLevel", secondLevel);
		query.setParameter("id", id);
		query.executeUpdate();
	}

}
