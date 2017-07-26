package com.ht.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ht.bean.EmpChecking;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.EmpCheckingService;

public class EmpCheckingAction extends BaseAction {

	private static final long serialVersionUID = -3637358222717631020L;

	private EmpCheckingService empCheckingService;
	private EmpChecking empChecking;
	private HttpServletRequest req;
	private List<EmpChecking> rows;
	private long total;
	private ControllerResult result;
	
	public EmpChecking getEmpChecking() {
		return empChecking;
	}

	public void setEmpChecking(EmpChecking empChecking) {
		this.empChecking = empChecking;
	}

	public List<EmpChecking> getRows() {
		return rows;
	}

	public void setEmpCheckingService(EmpCheckingService empCheckingService) {
		this.empCheckingService = empCheckingService;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	public ControllerResult getResult() {
		return result;
	}

	public long getTotal() {
		return total;
	}

	public void pager() {
		Pager4EasyUI<EmpChecking> pager = new Pager4EasyUI<EmpChecking>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = empCheckingService.queryByPager("EmpChecking", pager);
		rows = pager.getRows();
		if (rows == null) {
			System.out.println("null");
		} else {
			System.out.println("!Null");
		}
		total =  pager.getRows().size();
		req.setAttribute("Checkings", rows);
	}
	
	
}
