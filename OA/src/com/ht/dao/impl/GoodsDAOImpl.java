package com.ht.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ht.bean.Goods;
import com.ht.bean.GoodsType;
import com.ht.bean.info.GoodsInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.GoodsDAO;

public class GoodsDAOImpl extends AbstractBaseDAO<Goods> implements GoodsDAO {

	@Override
	public List<GoodsType> queryNameAll() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from GoodsType");
		@SuppressWarnings("unchecked")
		List<GoodsType> dept = query.list();
		return dept;
	}

	@Override
	public Pager4EasyUI<GoodsInfo> queryPayInfoPage(Pager4EasyUI<GoodsInfo> pager) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select g.goodsid,g.name,g.quantity,g.des,g.unitprice,g.buyday,gt.name as gt_name,gt.goodstypeid ,g.status from t_goods g ,t_goodstype gt where g.goodstypeid = gt.goodstypeid");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		List<GoodsInfo>goodsInfo = new ArrayList<GoodsInfo>();
		for (Object[] objs : list) {
			GoodsInfo info = new GoodsInfo();
			info.setGoodsId((String) objs[0]);
			info.setName((String) objs[1]);
			info.setQuantity((int) objs[2]);
			info.setDes((String) objs[3]);
			info.setUnitPrice((double) objs[4]);
			info.setBuyDay((Date) objs[5]);
			info.setGoodsTypeName((String) objs[6]);
			info.setGoodsTypeId((String) objs[7]);
			info.setStatus((int) objs[8]);
			goodsInfo.add(info);
		}
		pager.setRows(goodsInfo);
		return pager;
	}

	@Override
	public Goods queryQuantity(String beanName, String goodsName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from " + beanName + " where name='" + goodsName + "'");
		Goods goods = (Goods) query.uniqueResult();
		return goods;
	}

	@Override
	public void updateQuantity(String beanName, String goodsName, int count) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "update " + beanName + " set quantity = :count where name = :name";
		Query query = session.createQuery(hql);
		query.setParameter("count", count);
		query.setParameter("name", goodsName);
		query.executeUpdate();
	}

}
