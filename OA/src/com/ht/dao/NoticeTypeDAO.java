package com.ht.dao;

import com.ht.bean.NoticeType;
import com.ht.common.bean.Pager4EasyUI;

/**
 * 公告类型DAO
 * @author Administrator
 *
 */	
public interface NoticeTypeDAO extends BaseDAO<NoticeType>{

	/**
	 * 根据公告类型模糊搜索
	 * @param pager
	 * @param noticeTypeName
	 * @return
	 */
	public Pager4EasyUI<NoticeType> queryBynoticeTypeName(Pager4EasyUI<NoticeType> pager, String noticeTypeName);
}
