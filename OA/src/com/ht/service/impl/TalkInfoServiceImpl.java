package com.ht.service.impl;

import java.io.Serializable;

import com.ht.bean.info.TalkInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.TalkInfoDAO;
import com.ht.service.TalkInfoService;

public class TalkInfoServiceImpl implements TalkInfoService {

	private TalkInfoDAO tiDAO;
	
	public TalkInfoDAO getTiDAO() {
		return tiDAO;
	}

	public void setTiDAO(TalkInfoDAO tiDAO) {
		this.tiDAO = tiDAO;
	}

	@Override
	public Pager4EasyUI<TalkInfo> queryByPager(Pager4EasyUI<TalkInfo> pager) {
		pager = tiDAO.queryByPager(pager);
		pager.setTotal(tiDAO.count());
		return pager;
	}

	@Override
	public long count() {
		return tiDAO.count();
	}

	@Override
	public Pager4EasyUI<TalkInfo> queryByStuId(Pager4EasyUI<TalkInfo> pager, Serializable stuId) {
		pager = tiDAO.queryByStuId(pager, stuId);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

	@Override
	public Pager4EasyUI<TalkInfo> queryByDayAndStuId(Pager4EasyUI<TalkInfo> pager, Serializable stuId, String startDay,
			String endDay) {
		pager = tiDAO.queryByDayAndStuId(pager, stuId, startDay, endDay);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

}
