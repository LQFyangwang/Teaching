package com.ht.service;

import com.ht.bean.Room;
import com.ht.common.bean.Pager4EasyUI;

public interface RoomService extends BaseService<Room> {

	public Pager4EasyUI<Room> queryByRoomName(Pager4EasyUI<Room> pager, String roomName);
	
}
