package com.ht.dao;

import java.util.List;

import com.ht.bean.Notice;

/**
 * 公告表DAO
 * @author Administrator
 *
 */
public interface NoticeDAO extends BaseDAO<Notice>{

	/**
	 * 查询最新的前3公告
	 * @return
	 */
	public List<Notice> queryNewNotice();
}
