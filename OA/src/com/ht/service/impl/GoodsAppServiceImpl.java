package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.GoodsApp;
import com.ht.bean.info.GoodsAppInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.GoodsAppDAO;
import com.ht.service.GoodsAppService;

public class GoodsAppServiceImpl implements GoodsAppService{
	
	private GoodsAppDAO goodsAppDAO;
	

	public GoodsAppDAO getGoodsAppDAO() {
		return goodsAppDAO;
	}

	public void setGoodsAppDAO(GoodsAppDAO goodsAppDAO) {
		this.goodsAppDAO = goodsAppDAO;
	}

	@Override
	public void save(GoodsApp t) {
		goodsAppDAO.save(t);
	}

	@Override
	public void delete(GoodsApp t) {
		goodsAppDAO.delete(t);
	}

	@Override
	public void update(GoodsApp t) {
		goodsAppDAO.update(t);
	}

	@Override
	public GoodsApp queryById(Class<?> clazz, Serializable s) {
		return goodsAppDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<GoodsApp> queryByPager(String beanName, Pager4EasyUI<GoodsApp> pager) {
		pager = goodsAppDAO.queryByPager(beanName, pager);
		pager.setTotal(goodsAppDAO.count(beanName));
		return pager;
	}

	@Override
	public List<GoodsApp> queryAll(String beanName) {
		return goodsAppDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return goodsAppDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		goodsAppDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<GoodsApp> queryByStuName(Pager4EasyUI<GoodsApp> pager, Serializable stuName,
			Serializable beanObject) {
		return goodsAppDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<GoodsApp> queryByDay(Pager4EasyUI<GoodsApp> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return goodsAppDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<GoodsAppInfo> queryByPager(Pager4EasyUI<GoodsAppInfo> pager) {
		return goodsAppDAO.queryByPager(pager);
	}

	@Override
	public Pager4EasyUI<GoodsApp> queryByEmpName(Pager4EasyUI<GoodsApp> pager, Serializable empName,
			Serializable beanObject) {
		return goodsAppDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public Pager4EasyUI<GoodsAppInfo> queryBySelf(Pager4EasyUI<GoodsAppInfo> pager, Serializable empId) {
		return goodsAppDAO.queryBySelf(pager, empId);
	}

	@Override
	public void updateAppStatus(String beanName, String levelName, String idName, int secondLevel, String id) {
		goodsAppDAO.updateAppStatus(beanName, levelName, idName, secondLevel, id);
	}

}
