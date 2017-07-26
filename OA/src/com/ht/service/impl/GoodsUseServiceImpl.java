package com.ht.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ht.bean.GoodsUse;
import com.ht.bean.info.GoodsUseInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.GoodsUseDAO;
import com.ht.service.GoodsUseService;

public class GoodsUseServiceImpl implements GoodsUseService {
	
	public GoodsUseDAO goodsUseDAO;
	

	public GoodsUseDAO getGoodsUseDAO() {
		return goodsUseDAO;
	}

	public void setGoodsUseDAO(GoodsUseDAO goodsUseDAO) {
		this.goodsUseDAO = goodsUseDAO;
	}

	@Override
	public void save(GoodsUse t) {
		goodsUseDAO.save(t);
	}

	@Override
	public void delete(GoodsUse t) {
		goodsUseDAO.delete(t);
	}

	@Override
	public void update(GoodsUse t) {
		goodsUseDAO.update(t);
	}

	@Override
	public GoodsUse queryById(Class<?> clazz, Serializable s) {
		return goodsUseDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<GoodsUse> queryByPager(String beanName, Pager4EasyUI<GoodsUse> pager) {
		pager = goodsUseDAO.queryByPager(beanName, pager);
		pager.setTotal(goodsUseDAO.count(beanName));
		return pager;
	}

	@Override
	public List<GoodsUse> queryAll(String beanName) {
		return goodsUseDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return goodsUseDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		goodsUseDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<GoodsUse> queryByStuName(Pager4EasyUI<GoodsUse> pager, Serializable stuName,
			Serializable beanObject) {
		return goodsUseDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<GoodsUse> queryByEmpName(Pager4EasyUI<GoodsUse> pager, Serializable empName,
			Serializable beanObject) {
		return goodsUseDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public Pager4EasyUI<GoodsUse> queryByDay(Pager4EasyUI<GoodsUse> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return goodsUseDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<GoodsUseInfo> queryPayInfoPage(Pager4EasyUI<GoodsUseInfo> pager) {
		return goodsUseDAO.queryPayInfoPage(pager);
	}

	@Override
	public void updateReturn(int returnStatus, String id) {
		goodsUseDAO.updateReturn(returnStatus, id);
	}

	@Override
	public void updateReturnDay(Date ReturnDay, String id) {
		goodsUseDAO.updateReturnDay(ReturnDay, id);
	}

	@Override
	public void updateCheck(int checkStatus, String id) {
		goodsUseDAO.updateCheck(checkStatus, id);
	}

}
