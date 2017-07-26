package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Room;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.RoomDAO;
import com.ht.service.RoomService;

public class RoomServiceImpl implements RoomService {
	
	private RoomDAO roomDAO;
	

	public RoomDAO getRoomDAO() {
		return roomDAO;
	}

	public void setRoomDAO(RoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}

	@Override
	public void save(Room t) {
		roomDAO.save(t);
	}

	@Override
	public void delete(Room t) {
		roomDAO.delete(t);
	}

	@Override
	public void update(Room t) {
		roomDAO.update(t);
	}

	@Override
	public Room queryById(Class<?> clazz, Serializable s) {
		return roomDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Room> queryByPager(String beanName, Pager4EasyUI<Room> pager) {
		pager = roomDAO.queryByPager(beanName, pager);
		pager.setTotal(roomDAO.count(beanName));
		return pager;
	}

	@Override
	public List<Room> queryAll(String beanName) {
		return roomDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return roomDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		roomDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Room> queryByStuName(Pager4EasyUI<Room> pager, Serializable stuName, Serializable beanObject) {
		return roomDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Room> queryByDay(Pager4EasyUI<Room> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return roomDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<Room> queryByEmpName(Pager4EasyUI<Room> pager, Serializable empName, Serializable beanObject) {
		return roomDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public Pager4EasyUI<Room> queryByRoomName(Pager4EasyUI<Room> pager, String roomName) {
		return roomDAO.queryByRoomName(pager, roomName);
	}

}
