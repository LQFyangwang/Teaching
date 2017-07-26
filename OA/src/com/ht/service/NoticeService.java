package com.ht.service;

import java.util.List;

import com.ht.bean.Notice;
/**
 * 公告表Service
 * @author Administrator
 *
 */
public interface NoticeService extends BaseService<Notice>{

	/**
	 * 查询最新的3条公告
	 * @return
	 */
	public List<Notice> queryNewNotice();
}
