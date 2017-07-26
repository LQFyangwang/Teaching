package com.ht.action;

import java.util.List;

import com.ht.bean.Room;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.AuthorityRoleService;
import com.ht.service.RoomService;

public class RoomAction extends BaseAction {

	private static final long serialVersionUID = 7376172995568166768L;

	private RoomService roomService;
	private AuthorityRoleService authorityRoleService;
	private Room room;
	private List<Room> rows;
	private long total;
	private ControllerResult result;
	private String id;
	private String name;
	private String value;
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}

	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}

	public List<Room> getRows() {
		return rows;
	}

	public long getTotal() {
		return total;
	}

	public ControllerResult getResult() {
		return result;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String pager() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = RoomAction.class.getName() + ".pager";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Pager4EasyUI<Room> pager = new Pager4EasyUI<Room>();
			pager.setPageNo(WebUtil.getPageNo(req));
			pager.setPageSize(WebUtil.getPageSize(req));
			pager = roomService.queryByPager("Room", pager);
			pager.setTotal(roomService.count("Room"));
			rows = pager.getRows();
			total = pager.getTotal();
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "pager";
	}
	
	public String save() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = RoomAction.class.getName() + ".save";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			room.setStatus(1);
			roomService.save(room);
			result = ControllerResult.setSuccessResult("添加成功");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "save";
	}
	
	public String update() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = RoomAction.class.getName() + ".update";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			room.setStatus(1);
			roomService.update(room);
			result = ControllerResult.setSuccessResult("更新成功");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "update";
	}
	
	public String id() {
		roomService.queryById(Room.class, id);
		return "id";
	}
	
	public String all() {
		roomService.queryAll("Room");
		return "all";
	}
	
	public String frost() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = RoomAction.class.getName() + ".frost";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Room room = roomService.queryById(Room.class, id);
			if (room.getStatus() == 1) {
				result = ControllerResult.setSuccessResult("冻结成功");
				roomService.updateStatus("Room", "roomId", 0, id);
			} else {
				result = ControllerResult.setFailResult("已经被冻结了，不能再冻结");
			}
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
	
		return "status";
	}
	
	public String activation() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = RoomAction.class.getName() + ".activation";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Room room = roomService.queryById(Room.class, id);
			if (room.getStatus() == 0) {
				result = ControllerResult.setSuccessResult("激活成功");
				roomService.updateStatus("Room", "roomId", 1, id);
			} else {
				result = ControllerResult.setFailResult("已经激活了，不能再激活");
			}
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "status";
	}
	
	/**
	 * 模糊搜索
	 * @return
	 */
	public String fuzzySearch() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = RoomAction.class.getName() + ".fuzzySearch";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Pager4EasyUI<Room> pager = new Pager4EasyUI<Room>();
			pager.setPageNo(WebUtil.getPageNo(req));
			pager.setPageSize(WebUtil.getPageSize(req));
			if (name.equals("roomName")) {
				pager = roomService.queryByRoomName(pager, value);
			}
			rows = pager.getRows();
			total = pager.getTotal();
			if (rows != null) {
				result = ControllerResult.setSuccessResult("查找成功");
			} else {
				result = ControllerResult.setFailResult("没有记录");
			}
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "fuzzySearch";
	}
	
}
