package com.ht.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.GoodsUse;
import com.ht.bean.info.GoodsUseInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.GoodsUseDAO;

public class GoodsUseDAOImpl extends AbstractBaseDAO<GoodsUse> implements GoodsUseDAO{

	@Override
	public Pager4EasyUI<GoodsUseInfo> queryPayInfoPage(Pager4EasyUI<GoodsUseInfo> pager) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select g.goodsid,g.name as name_1 ,gu.useid,gu.quantity,gu.useday,gu.ereturnday,gu.returnday,e.empid,e.name as name_2, gu.returnstatus, gu.checkstatus from t_goods g,t_emp e,t_goodsuse gu where gu.empid = e.empid and gu.goodsid = g.goodsid");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		List<GoodsUseInfo>goodsUseInfo = new ArrayList<GoodsUseInfo>();
		for(Object[] objs : list){
			GoodsUseInfo goodsUse = new GoodsUseInfo();
			goodsUse.setGoodsId((String) objs[0]);
			goodsUse.setGoodsName((String) objs[1]);
			goodsUse.setUseId((String) objs[2]);
			goodsUse.setQuantity((int) objs[3]);
			goodsUse.setUseDay((Date) objs[4]);
			goodsUse.setEreturnDay((Date) objs[5]);
			goodsUse.setReturnDay((Date) objs[6]);
			goodsUse.setEmpId((String) objs[7]);
			goodsUse.setEmpName((String) objs[8]);
			goodsUse.setReturnStatus((int) objs[9]);
			goodsUse.setCheckStatus((int) objs[10]);
			goodsUseInfo.add(goodsUse);
		}
		pager.setRows(goodsUseInfo);
		return pager;
	}

	@Override
	public void updateReturn(int returnStatus, String id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "update GoodsUse set returnStatus= :return where useId = :id";
		Query query = session.createQuery(hql);
		query.setParameter("return", returnStatus);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public void updateReturnDay(Date ReturnDay, String id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "update GoodsUse set returnDay= :return where useId = :id";
		Query query = session.createQuery(hql);
		query.setParameter("return", ReturnDay);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public void updateCheck(int checkStatus, String id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "update GoodsUse set checkstatus= :check where useId = :id";
		Query query = session.createQuery(hql);
		query.setParameter("check", checkStatus);
		query.setParameter("id", id);
		query.executeUpdate();
	}
}
