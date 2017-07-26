package com.ht.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.ht.bean.GoodsType;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.AuthorityRoleService;
import com.ht.service.GoodsTypeService;

public class GoodsTypeAction extends BaseAction {

	private static final long serialVersionUID = -8141056164096745048L;
	
	private GoodsTypeService goodsTypeService;
	private HttpServletRequest req;
	private GoodsType goodsType;
	private ControllerResult result;
	private List<GoodsType> rows; // 返回给easyui的结果
	private long total; // 返回给easyui的总页数
	private String id;
	private AuthorityRoleService authorityRoleService;
	
	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}
	public void setId(String id) {
		this.id = id;
	}

	public ControllerResult getResult() {
		return result;
	}
	
	public List<GoodsType> getRows() {
		return rows;
	}

	public long getTotal() {
		return total;
	}

	public void setGoodsTypeService(GoodsTypeService goodsTypeService) {
		this.goodsTypeService = goodsTypeService;
	}
	
	public void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}

	public GoodsType getGoodsType() {
		return goodsType;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}
	
	public String goodsTypeSave(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = GoodsTypeAction.class.getName() + ".goodsTypeSave";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "TypeSave";
		}
		goodsType.setStatus(1);
		goodsTypeService.save(goodsType);
		result = ControllerResult .setSuccessResult("添加物品类型成功");
		return "TypeSave";
	}
	public String goodsTypePage(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = GoodsTypeAction.class.getName() + ".goodsTypePage";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "pager";
		}
		Pager4EasyUI<GoodsType> pager = new Pager4EasyUI<GoodsType>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = goodsTypeService.queryByPager("GoodsType", pager);
		pager.setTotal(goodsTypeService.count("GoodsType"));
		rows = pager.getRows();
		total = pager.getTotal();
		return "pager";
	}
	
	public String goodsUpdate(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = GoodsTypeAction.class.getName() + ".goodsUpdate";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "update";
		}
		goodsType.setStatus(1);
		goodsTypeService.update(goodsType);
		result = ControllerResult .setSuccessResult("修改成功");
		return "update";
	}
	
	public String frost(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = GoodsTypeAction.class.getName() + ".frost";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "id";
		}
		GoodsType goodsType = goodsTypeService.queryById(GoodsType.class, id);
		if (goodsType.getStatus() == 1) {
			result = ControllerResult.setSuccessResult("冻结成功");
			goodsTypeService.updateStatus("GoodsType", "goodstypeid", 0, id);
		} else {
			result = ControllerResult.setFailResult("已经被冻结了，不能再冻结");
		}
		return "id";
	}
	public String activation(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = GoodsTypeAction.class.getName() + ".activation";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "id";
		}
		GoodsType goodsType = goodsTypeService.queryById(GoodsType.class, id);
		if (goodsType.getStatus() == 0) {
			result = ControllerResult.setSuccessResult("激活成功");
			goodsTypeService.updateStatus("GoodsType", "goodstypeid", 1, id);
		} else {
			result = ControllerResult.setFailResult("已经被激活了，不能再激活");
		}
		return "id";
	}
}
