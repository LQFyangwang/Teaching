package com.ht.dao;

import com.ht.bean.Room;
import com.ht.common.bean.Pager4EasyUI;

public interface RoomDAO extends BaseDAO<Room> {
	
	/**
	 * 根据宿舍名称模糊搜索
	 * @param pager
	 * @param roomName
	 * @return
	 */
	public Pager4EasyUI<Room> queryByRoomName(Pager4EasyUI<Room> pager, String roomName);

}
