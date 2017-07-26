package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Goods;
import com.ht.bean.GoodsType;
import com.ht.bean.info.GoodsInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.GoodsDAO;
import com.ht.service.GoodsService;

public class GoodsServiceImpl implements GoodsService{
	
	private GoodsDAO goodsDAO;
	
	public GoodsDAO getGoodsDAO() {
		return goodsDAO;
	}

	public void setGoodsDAO(GoodsDAO goodsDAO) {
		this.goodsDAO = goodsDAO;
	}

	@Override
	public void save(Goods t) {
		goodsDAO.save(t);
	}

	@Override
	public void delete(Goods t) {
		goodsDAO.delete(t);
	}

	@Override
	public void update(Goods t) {
		goodsDAO.update(t);
	}

	@Override
	public Goods queryById(Class<?> clazz, Serializable s) {
		return goodsDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Goods> queryByPager(String beanName, Pager4EasyUI<Goods> pager) {
		pager = goodsDAO.queryByPager(beanName, pager);
		pager.setTotal(goodsDAO.count(beanName));
		return pager;
	}

	@Override
	public List<Goods> queryAll(String beanName) {
		return goodsDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return goodsDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		goodsDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public List<GoodsType> queryNameAll() {
		return goodsDAO.queryNameAll();
	}

	@Override
	public Pager4EasyUI<GoodsInfo> queryPayInfoPage(Pager4EasyUI<GoodsInfo> pager) {
		return goodsDAO.queryPayInfoPage(pager);
	}

	@Override
	public Pager4EasyUI<Goods> queryByStuName(Pager4EasyUI<Goods> pager, Serializable stuName,
			Serializable beanObject) {
		return  goodsDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Goods> queryByDay(Pager4EasyUI<Goods> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return  goodsDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<Goods> queryByEmpName(Pager4EasyUI<Goods> pager, Serializable empName,
			Serializable beanObject) {
		return goodsDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public Goods queryQuantity(String beanName, String goodsName) {
		return goodsDAO.queryQuantity(beanName, goodsName);
	}

	@Override
	public void updateQuantity(String beanName, String goodsName, int count) {
		goodsDAO.updateQuantity(beanName, goodsName, count);
	}

}
