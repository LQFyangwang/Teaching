package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Notice;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.NoticeDAO;
import com.ht.service.NoticeService;

public class NoticeServiceImpl implements NoticeService{

	private NoticeDAO noticeDAO;
	
	public NoticeDAO getNoticeDAO() {
		return noticeDAO;
	}

	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}

	@Override
	public void save(Notice t) {
		noticeDAO.save(t);
	}

	@Override
	public void delete(Notice t) {
		noticeDAO.delete(t);
	}

	@Override
	public void update(Notice t) {
		noticeDAO.update(t);
	}

	@Override
	public Notice queryById(Class<?> clazz, Serializable s) {
		return noticeDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Notice> queryByPager(String beanName, Pager4EasyUI<Notice> pager) {
		pager = noticeDAO.queryByPager(beanName, pager);
		pager.setTotal(noticeDAO.count(beanName));
		return pager;
	}

	@Override
	public List<Notice> queryAll(String beanName) {
		return noticeDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return noticeDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		noticeDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Notice> queryByStuName(Pager4EasyUI<Notice> pager, Serializable stuName,
			Serializable beanObject) {
		return noticeDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Notice> queryByDay(Pager4EasyUI<Notice> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return noticeDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<Notice> queryByEmpName(Pager4EasyUI<Notice> pager, Serializable empName,
			Serializable beanObject) {
		return noticeDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public List<Notice> queryNewNotice() {
		return noticeDAO.queryNewNotice();
	}

}
