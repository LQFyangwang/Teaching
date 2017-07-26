package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.NoticeType;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.NoticeTypeDAO;
import com.ht.service.NoticeTypeService;

public class NoticeTypeServiceImpl  implements NoticeTypeService{

	private NoticeTypeDAO noticeTypeDAO;
	
	public NoticeTypeDAO getNoticeTypeDAO() {
		return noticeTypeDAO;
	}

	public void setNoticeTypeDAO(NoticeTypeDAO noticeTypeDAO) {
		this.noticeTypeDAO = noticeTypeDAO;
	}

	@Override
	public void save(NoticeType t) {
		noticeTypeDAO.save(t);
	}

	@Override
	public void delete(NoticeType t) {
		noticeTypeDAO.delete(t);
	}

	@Override
	public void update(NoticeType t) {
		noticeTypeDAO.update(t);
	}

	@Override
	public NoticeType queryById(Class<?> clazz, Serializable s) {
		return noticeTypeDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<NoticeType> queryByPager(String beanName, Pager4EasyUI<NoticeType> pager) {
		pager = noticeTypeDAO.queryByPager(beanName, pager);
		pager.setTotal(noticeTypeDAO.count(beanName));
		return pager;
	}

	@Override
	public List<NoticeType> queryAll(String beanName) {
		return noticeTypeDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return noticeTypeDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		noticeTypeDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<NoticeType> queryByStuName(Pager4EasyUI<NoticeType> pager, Serializable stuName,
			Serializable beanObject) {
		return noticeTypeDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<NoticeType> queryByDay(Pager4EasyUI<NoticeType> pager, Serializable startDay,
			Serializable endDay, Serializable beanObject, Serializable attrName) {
		return noticeTypeDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<NoticeType> queryByEmpName(Pager4EasyUI<NoticeType> pager, Serializable empName,
			Serializable beanObject) {
		return noticeTypeDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public Pager4EasyUI<NoticeType> queryBynoticeTypeName(Pager4EasyUI<NoticeType> pager, String noticeTypeName) {
		return noticeTypeDAO.queryBynoticeTypeName(pager, noticeTypeName);
	}

}
