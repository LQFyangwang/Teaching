package com.ht.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ht.bean.Emp;
import com.ht.bean.SalaryInfo;
import com.ht.bean.info.EmpSalaryInfo;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.EmpSalaryInfoService;
import com.ht.service.EmpService;
import com.ht.service.SalaryInfoService;

public class SalaryInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest req;
	private SalaryInfo mysi;
	private EmpSalaryInfoService esiService;
	private SalaryInfoService siService;
	private List<EmpSalaryInfo> rows;
	private List<SalaryInfo> rowss;
	private long total;
	private ControllerResult result;
	private EmpSalaryInfo esi;
	private String id;
	private EmpService empService;
	
	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}
	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}

	public List<SalaryInfo> getRowss() {
		return rowss;
	}

	public void setSiService(SalaryInfoService siService) {
		this.siService = siService;
	}
	public SalaryInfo getMysi() {
		return mysi;
	}
	public void setMysi(SalaryInfo mysi) {
		this.mysi = mysi;
	}
	public List<EmpSalaryInfo> getRows() {
		return rows;
	}
	public long getTotal() {
		return total;
	}
	public void setEsiService(EmpSalaryInfoService esiService) {
		this.esiService = esiService;
	}
	public ControllerResult getResult() {
		return result;
	}
	public EmpSalaryInfo getEsi() {
		return esi;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String query_page() {
		Pager4EasyUI<EmpSalaryInfo> pager = new Pager4EasyUI<EmpSalaryInfo>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = esiService.queryEmpSalaryInfoPage(1, pager);
		pager.setTotal(esiService.countStatus(1));
		rows = pager.getRows();
		total = pager.getTotal();
		return "query_page";
	}
	
	public String query_salaryinfo() {
		System.out.println("id====="+id);
		Pager4EasyUI<SalaryInfo> pager = new Pager4EasyUI<SalaryInfo>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = siService.querySalaryInfoPage(id, pager);
		pager.setTotal(siService.countEmp(id));
		rowss = pager.getRows();
		total = pager.getTotal();
		return "query_salaryinfo";
	}
	
	public String query_si() {
		Pager4EasyUI<SalaryInfo> pager = new Pager4EasyUI<SalaryInfo>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = siService.queryByPager("SalaryInfo", pager);
		pager.setTotal(siService.count("SalaryInfo"));
		rowss = pager.getRows();
		total = pager.getTotal();
		return "query_si";
	}
	
	public String save() {
//		si.setEmp(emp);
//		String id = req.getParameter("si.emp.empId");
//		System.out.println("id === " + id);
		Emp emp = new Emp();
		emp.setEmpId(mysi.getEmp().getEmpId());
		mysi.setEmp(emp);
		siService.save(mysi);
		result = ControllerResult.setSuccessResult("添加员工工资信息成功");
		return "save";
	}
	
	public String update() {
		Emp emp = new Emp();
		emp.setEmpId(mysi.getEmp().getEmpId());
		emp.setAccountNo(mysi.getEmp().getAccountNo());
		emp.setBankName(mysi.getEmp().getBankName());
		mysi.setEmp(emp);
		empService.updateSalaryInfo(emp);
		siService.update(mysi);
		result = ControllerResult.setSuccessResult("更新员工工资信息成功");
		return "update";
	}
	
	public String query_name() {
		String value = req.getParameter("name");
		String value2 = req.getParameter("bankName");
		String value3 = req.getParameter("accountNo");
		String value4 = req.getParameter("jobSalary");
		Pager4EasyUI<EmpSalaryInfo> pager = new Pager4EasyUI<EmpSalaryInfo>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		if(value != null) {
			pager = esiService.queryEmpSalaryInfoName(1, pager, "e.name", value);
			pager.setTotal(esiService.countName(1, "e.name", value));
			rows = pager.getRows();
			total = pager.getTotal();
		} else if(value2 != null) {
			pager = esiService.queryEmpSalaryInfoName(1, pager, "e.bankname", value2);
			pager.setTotal(esiService.countName(1, "e.bankname", value2));
			rows = pager.getRows();
			total = pager.getTotal();
		} else if(value3 != null) {
			pager = esiService.queryEmpSalaryInfoName(1, pager, "e.accountno", value3);
			pager.setTotal(esiService.countName(1, "e.accountno", value3));
			rows = pager.getRows();
			total = pager.getTotal();
		} else if(value4 != null) {
			pager = esiService.queryEmpSalaryInfoName(1, pager, "si.jobsalary", value4);
			pager.setTotal(esiService.countName(1, "si.jobsalary", value4));
			rows = pager.getRows();
			total = pager.getTotal();
		}
		return "query_name";
	}
	

}
