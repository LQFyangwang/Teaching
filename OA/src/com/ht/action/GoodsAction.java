package com.ht.action;

import java.util.ArrayList;
import java.util.List;

import com.ht.bean.Goods;
import com.ht.bean.GoodsType;
import com.ht.bean.info.GoodsInfo;
import com.ht.common.DateUtil;
import com.ht.common.bean.ComboBox4EasyUI;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.AuthorityRoleService;
import com.ht.service.GoodsService;

public class GoodsAction extends BaseAction {
	
	private static final long serialVersionUID = 7381625177422265039L;
	
	private GoodsService goodsService;
	private Goods goods;
	private List<GoodsInfo> rows;
	private long total;
	private ControllerResult result;
	private List<ComboBox4EasyUI> results;
	private String id;
	private AuthorityRoleService authorityRoleService;
	
	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}
	public void setId(String id) {
		this.id = id;
	}

	public List<ComboBox4EasyUI> getResults() {
		return results;
	}
	
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
	public long getTotal() {
		return total;
	}

	public ControllerResult getResult() {
		return result;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}


	public List<GoodsInfo> getRows() {
		return rows;
	}

	public String goodsAdd(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = GoodsAction.class.getName() + ".goodsAdd";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "add";
		}
		goods.setStatus(1);
		goods.setBuyDay(DateUtil.getDate());
		goodsService.save(goods);
		result = ControllerResult.setSuccessResult("添加用品成功");
		return "add";
	}
	
	public String goodsPage(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = GoodsAction.class.getName() + ".goodsPage";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "pager";
		}
		Pager4EasyUI<GoodsInfo> pager = new Pager4EasyUI<GoodsInfo>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = goodsService.queryPayInfoPage(pager);
		pager.setTotal(goodsService.count("Goods"));
		rows = pager.getRows();
		total = pager.getTotal();
		return "pager";
	}
	public String goodsUpdate(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = GoodsAction.class.getName() + ".goodsUpdate";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "updateGoods";
		}
		goods.setStatus(1);
		goodsService.update(goods);
		result = ControllerResult.setSuccessResult("更新用品成功");
		return "updateGoods";
	}
	public String goodsType(){
		List<GoodsType> depts = new ArrayList<GoodsType>();
		results = new ArrayList<ComboBox4EasyUI>();
		depts = goodsService.queryNameAll();
		for(GoodsType de : depts){
			ComboBox4EasyUI bobox = new ComboBox4EasyUI();
			bobox.setId(de.getGoodsTypeId());
			if(de.getGoodsTypeId().equals(req.getParameter("TypeId"))){
				bobox.setSelected(true);
			};
			bobox.setText(de.getName());
			results.add(bobox);
		}
		return "goodsType";
	}
	
	public String frost(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = GoodsAction.class.getName() + ".frost";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "frost";
		}
		Goods goods = goodsService .queryById(Goods.class, id);
		if (goods.getStatus() == 1) {
			result = ControllerResult.setSuccessResult("冻结成功");
			goodsService.updateStatus("Goods", "goodsId", 0, id);
		} else {
			result = ControllerResult.setFailResult("已经被冻结了，不能再冻结");
		}
		return "frost";
	}
	
	public String activation(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = GoodsAction.class.getName() + ".activation";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			return "activation";
		}
		Goods goods = goodsService .queryById(Goods.class, id);
		if (goods.getStatus() == 0) {
			result = ControllerResult.setSuccessResult("激活成功");
			goodsService.updateStatus("Goods", "goodsId", 1, id);
		} else {
			result = ControllerResult.setFailResult("已经被激活了，不能再激活");
		}
		return "activation";
	}
}
