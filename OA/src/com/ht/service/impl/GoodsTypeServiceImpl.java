package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.GoodsType;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.GoodsTypeDAO;
import com.ht.service.GoodsTypeService;

public class GoodsTypeServiceImpl  implements GoodsTypeService{
	
	public GoodsTypeDAO goodsTypeDAO;
	
	public GoodsTypeDAO getGoodsTypeDAO() {
		return goodsTypeDAO;
	}

	public void setGoodsTypeDAO(GoodsTypeDAO goodsTypeDAO) {
		this.goodsTypeDAO = goodsTypeDAO;
	}

	@Override
	public void save(GoodsType t) {
		goodsTypeDAO.save(t);
	}

	@Override
	public void delete(GoodsType t) {
		goodsTypeDAO.delete(t);
	}

	@Override
	public void update(GoodsType t) {
		goodsTypeDAO.update(t);
	}

	@Override
	public GoodsType queryById(Class<?> clazz, Serializable s) {
		return goodsTypeDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<GoodsType> queryByPager(String beanName, Pager4EasyUI<GoodsType> pager) {
		pager = goodsTypeDAO.queryByPager(beanName, pager);
		pager.setTotal(goodsTypeDAO.count(beanName));
		return pager;
	}

	@Override
	public List<GoodsType> queryAll(String beanName) {
		return goodsTypeDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return goodsTypeDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		goodsTypeDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<GoodsType> queryByStuName(Pager4EasyUI<GoodsType> pager, Serializable stuName,
			Serializable beanObject) {
		return goodsTypeDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<GoodsType> queryByDay(Pager4EasyUI<GoodsType> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return goodsTypeDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<GoodsType> queryByEmpName(Pager4EasyUI<GoodsType> pager, Serializable empName,
			Serializable beanObject) {
		return goodsTypeDAO.queryByEmpName(pager, empName, beanObject);
	}

}
