package com.ht.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.ht.bean.Emp;
import com.ht.bean.Salary;
import com.ht.bean.SalaryInfo;
import com.ht.bean.info.SalaryInfo1;
import com.ht.common.Constants;
import com.ht.common.SalaryUtil;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.ExportExcel;
import com.ht.common.web.WebUtil;
import com.ht.service.AuthorityRoleService;
import com.ht.service.SalaryInfoService;
import com.ht.service.SalaryInfoService1;
import com.ht.service.SalaryService;

public class SalaryInfoAction1 extends BaseAction {

	private static final long serialVersionUID = -568585051781682343L;

	private SalaryInfo1 salaryInfo;
	private SalaryInfoService1 salaryInfoService1;
	private AuthorityRoleService authorityRoleService;
	private SalaryService salaryService;
	private SalaryInfoService siService;
	private List<SalaryInfo1> rows;
	private long total;
	private ControllerResult result;
	
	private String empName;
	private String startDay;
	private String endDay;
	
	private HttpSession session;
	
	private int number;
	private String fileName;
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public InputStream getInputStream() throws Exception {
		FileInputStream inputStream = null;
		if (number == 1) {
			fileName = "slary.xls";
			inputStream = new FileInputStream(new File(Constants.PATH_EXCEL + Constants.SALARY_NAME));
		}
		return inputStream;
	}

	public SalaryInfo1 getSalaryInfo() {
		return salaryInfo;
	}

	public void setSalaryInfo(SalaryInfo1 salaryInfo) {
		this.salaryInfo = salaryInfo;
	}

	public List<SalaryInfo1> getRows() {
		return rows;
	}

	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}

	public long getTotal() {
		return total;
	}

	public ControllerResult getResult() {
		return result;
	}

	public void setSalaryInfoService1(SalaryInfoService1 salaryInfoService1) {
		this.salaryInfoService1 = salaryInfoService1;
	}

	public void setSalaryService(SalaryService salaryService) {
		this.salaryService = salaryService;
	}

	public void setSiService(SalaryInfoService siService) {
		this.siService = siService;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String pager() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = SalaryInfoAction1.class.getName() + ".pager";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			setPager("pager");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		
		return "pager";
	}
	
	private void setSalary(String method) {
		String empId = salaryInfo.getEmpId();
		double basicSalary = salaryInfo.getBasicSalary();
		double jobSalary = salaryInfo.getJobSalary();
		double extraSalary = salaryInfo.getExtraSalary();
		double subSalary = salaryInfo.getSubSalary();
		
//		SalaryInfo salaryInfo1 = new SalaryInfo();
//		Emp emp = new Emp();
//		emp.setEmpId(empId);
//		salaryInfo1.setEmp(emp);
//		salaryInfo1.setBasicSalary(basicSalary);
//		salaryInfo1.setJobSalary(jobSalary);
		
		Salary salary = new Salary();
		salary.setEmpId(empId);
		salary.setExtraSalary(extraSalary);
		salary.setSubSalary(subSalary);
		salary.setSalaryDay(salaryInfo.getSalaryDay());
		salary.setTotalSalary(SalaryUtil.totalSalary(basicSalary, jobSalary, extraSalary, subSalary));
		
		if (method.equals("add")) {
//			siService.save(salaryInfo1);
			salaryService.save(salary);
			result = ControllerResult.setSuccessResult("添加成功");
		} else if (method.equals("edit")) {
			salary.setSalaryId(salaryInfo.getSalaryId());
//			salaryInfo1.setSalaryinfoId(salaryInfo.getSalaryInfoId());
//			siService.update(salaryInfo1);
			salaryService.update(salary);
			result = ControllerResult.setSuccessResult("更新成功");
		}
	}
	
	public String add() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = SalaryInfoAction1.class.getName() + ".add";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			setSalary("add");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		
		return "add";
	}
	
	public String edit() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = SalaryInfoAction1.class.getName() + ".edit";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			setSalary("edit");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		
		return "edit";
	}
	
	public String pagerByName() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = SalaryInfoAction1.class.getName() + ".pagerByName";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			setPager("queryByName");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		
		return "pagerByName";
	}
	
	public String pagerByDay() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = SalaryInfoAction1.class.getName() + ".pagerByDay";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			setPager("queryByDay");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		
		return "pagerByDay";
	}
	
	private void setPager(String method) {
		session = req.getSession();
		String role = WebUtil.getRole(req);
		Pager4EasyUI<SalaryInfo1> pager = new Pager4EasyUI<SalaryInfo1>();;
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		if (method.equals("pager")) {
			if(role.equals("总经理") || role.equals("财务主任")) {
				pager = salaryInfoService1.queryByPager(pager);
			} else {
				Emp emp = (Emp) session.getAttribute("emp");
				pager = salaryInfoService1.queryPageEmpId(pager, emp.getEmpId());
			}
		} else if (method.equals("queryByDay")) {
			if (role.equals("总经理") || role.equals("财务主任")) {
				pager = salaryInfoService1.queryByDay(pager, startDay, endDay);
			} else {
				Emp emp = (Emp) session.getAttribute("emp");
				pager = salaryInfoService1.queryByDayAndEmpId(pager, startDay, startDay, emp.getEmpId());
			}
		} else if (method.equals("queryByName")) {
			pager = salaryInfoService1.queryByName(pager, empName);
		}
		total = pager.getTotal();
		rows = pager.getRows();
		if (rows != null && rows.size() > 0) {
			result = ControllerResult.setSuccessResult("查找成功");
		} else {
			result = ControllerResult.setFailResult("没有记录");
		}
		
	}
	
	public String exportData() throws Exception {
		session = req.getSession();
		String role = WebUtil.getRole(req);
		Pager4EasyUI<SalaryInfo1> pager = new Pager4EasyUI<SalaryInfo1>();;
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		if(role.equals("总经理") || role.equals("财务主任")) {
			pager = salaryInfoService1.queryByPager(pager);
		} else {
			Emp emp = (Emp) session.getAttribute("emp");
			pager = salaryInfoService1.queryPageEmpId(pager, emp.getEmpId());
		}
		rows = pager.getRows();
		if (rows != null && rows.size() > 0) {
			Map<String,String> titleMap = new LinkedHashMap<String,String>();
			titleMap.put("empName", "员工姓名");
			titleMap.put("basicSalary", "基本工资");
			titleMap.put("jobSalary", "岗位工资");
			titleMap.put("extraSalary", "奖励工资");
			titleMap.put("subSalary", "扣罚工资");
			titleMap.put("shouldSalary", "实发工资");
			titleMap.put("totalSalary", "应发工资");
			titleMap.put("salaryDay", "发放时间");
			String sheetName = "员工工资";
			File file = new File(Constants.PATH_EXCEL);
			if (!file.exists()) {
				file.mkdir();
			}
			ExportExcel.excelExport(rows, titleMap, sheetName, "salary", Constants.PATH_EXCEL);
			// ExcelUtil.excleOut(pager.getRows(), Constants.PATH_EXCEL + Constants.SALARY_NAME);
			result = ControllerResult.setSuccessResult("导出成功");
		} else {
			result = ControllerResult.setFailResult("导出失败");
		}
		return "exportData";
	}
	
}
