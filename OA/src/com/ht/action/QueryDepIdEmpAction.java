package com.ht.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ht.bean.Emp;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.EmpService;

public class QueryDepIdEmpAction extends BaseAction {

	private static final long serialVersionUID = -1989773278996001718L;

	private Emp emp;
	private EmpService empService;
	private HttpServletRequest req;
	private long total;
	private List<Emp> rows;
	private String depId;
	
	public void setDepId(String depId) {
		this.depId = depId;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}
	
	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public long getTotal() {
		return total;
	}

	public List<Emp> getRows() {
		return rows;
	}

	public void setempService(EmpService empService) {
		this.empService = empService;
	}
	
	public String pager() {
		Pager4EasyUI<Emp> pager = new Pager4EasyUI<Emp>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = empService.queryByPager2(pager, depId);
		rows = pager.getRows();
		total = pager.getRows().size();
		return "pager";
	}
}
