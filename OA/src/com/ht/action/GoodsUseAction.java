package com.ht.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ht.bean.Goods;
import com.ht.bean.GoodsUse;
import com.ht.bean.info.GoodsUseInfo;
import com.ht.common.DateUtil;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.GoodsService;
import com.ht.service.GoodsUseService;

public class GoodsUseAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6308818748646929191L;
	
	private GoodsUse goodsUse;
	private GoodsUseService goodsUseService;
	private GoodsService goodsService;
	private ControllerResult result;
	private List<GoodsUseInfo> rows;
	private long total;
	private String id;
	private String goodsName;
	
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	public long getTotal() {
		return total;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public List<GoodsUseInfo> getRows() {
		return rows;
	}

	public ControllerResult getResult() {
		return result;
	}

	public void setGoodsUseService(GoodsUseService goodsUseService) {
		this.goodsUseService = goodsUseService;
	}

	public GoodsUse getGoodsUse() {
		return goodsUse;
	}

	public void setGoodsUse(GoodsUse goodsUse) {
		this.goodsUse = goodsUse;
	}
	
	public String GoodsUseSave(){
		String empId = req.getParameter("emp.empId");
		String goodsId = req.getParameter("goods.goodsId");
		goodsUse.setEmpId(empId);
		goodsUse.setGoodsId(goodsId);
		goodsUse.setUseDay(DateUtil.getDate());
		goodsUse.setReturnStatus(0);
		goodsUse.setCheckStatus(2);
		goodsUseService.save(goodsUse);
		result = ControllerResult.setSuccessResult("添加申请成功");
		return "goodsUseSave";
	}
	
	public String GoodsUsePage(){
		Pager4EasyUI<GoodsUseInfo>pager = new Pager4EasyUI<GoodsUseInfo>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = goodsUseService.queryPayInfoPage(pager);
		if (!WebUtil.getRole(req).equals("总经理") && !WebUtil.getRole(req).equals("后勤主任")) {
			List<GoodsUseInfo> guis = new ArrayList<GoodsUseInfo>();
			for (GoodsUseInfo gui : pager.getRows()) {
				if (gui.getEmpId().equals(WebUtil.getEmp(req).getEmpId())) {
					guis.add(gui);
				}
			}
			pager.setRows(guis);
		}
		rows = pager.getRows();
		total = rows.size();
		return "goodsUsePage";
	}
	
	public String GoodsUseUpdate(){
		GoodsUse goo = goodsUseService.queryById(GoodsUse.class, id);
		goo.setUseId(req.getParameter("id"));
		return "update";
	}
	
	public String updateReturn() {
		Goods g = goodsService.queryQuantity("Goods", goodsName);
		System.out.println(goodsUse.getUseId());
		goodsUseService.updateReturn(1, goodsUse.getUseId());
		goodsUseService.updateReturnDay(new Date(), goodsUse.getUseId());
		goodsService.updateQuantity("Goods", goodsName, (g.getQuantity() + goodsUse.getQuantity()));
		result = ControllerResult.setSuccessResult("归还成功");
		return "updateReturn";
	}
	
	public String acceptCheck() {
		goodsUseService.updateCheck(1, id);
		Goods g = goodsService.queryQuantity("Goods", goodsName);
		goodsService.updateQuantity("Goods", goodsName, (g.getQuantity() - goodsUse.getQuantity()));
		goodsUseService.updateReturn(2, id);
		result = ControllerResult.setSuccessResult("同意成功");
		return "updateCheck";
	}
	
	public String refuseCheck() {
		goodsUseService.updateCheck(0, id);
		result = ControllerResult.setSuccessResult("回绝成功");
		return "updateCheck";
	}
}
