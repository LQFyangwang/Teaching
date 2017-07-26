package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Talk;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.TalkDAO;
import com.ht.service.BaseService;
import com.ht.service.TalkService;

public class TalkServiceImpl implements BaseService<Talk>, TalkService {

	private TalkDAO talkDAO;
	
	public TalkDAO getTalkDAO() {
		return talkDAO;
	}

	public void setTalkDAO(TalkDAO talkDAO) {
		this.talkDAO = talkDAO;
	}

	@Override
	public void save(Talk t) {
		talkDAO.save(t);
	}

	@Override
	public void delete(Talk t) {
		talkDAO.delete(t);
	}

	@Override
	public void update(Talk t) {
		talkDAO.update(t);
	}

	@Override
	public Talk queryById(Class<?> clazz, Serializable s) {
		return talkDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Talk> queryByPager(String beanName, Pager4EasyUI<Talk> pager) {
		pager = talkDAO.queryByPager(beanName, pager);
		pager.setTotal(talkDAO.count(beanName));
		return pager;
	}

	@Override
	public List<Talk> queryAll(String beanName) {
		return talkDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return talkDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		talkDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Talk> queryByStuName(Pager4EasyUI<Talk> pager, Serializable stuName, Serializable beanObject) {
		return talkDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Talk> queryByDay(Pager4EasyUI<Talk> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return talkDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<Talk> queryByEmpName(Pager4EasyUI<Talk> pager, Serializable empName, Serializable beanObject) {
		return talkDAO.queryByEmpName(pager, empName, beanObject);
	}

}
