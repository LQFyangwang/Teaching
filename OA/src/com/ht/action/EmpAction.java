package com.ht.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.ht.bean.Emp;
import com.ht.common.EncryptUtil;
import com.ht.common.bean.ComboBox4EasyUI;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.AuthorityRoleService;
import com.ht.service.EmpService;
import com.ht.service.RoleService;
import com.opensymphony.xwork2.ActionSupport;

public class EmpAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = -8879843492351804907L;

	private HttpServletRequest req;
	private Emp emp;
	private EmpService empService;
//	private EmpInfoService empInfoService;
	private long total;
	private ControllerResult result;
//	private List<EmpInfo> rows;
	private List<Emp> rows;
	private String id;
	private List<ComboBox4EasyUI> cobox;
	private RoleService roleService;
	private int check;
	private AuthorityRoleService authorityRoleService;
	
	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}

	
	public void setCheck(int check) {
		this.check = check;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setId(String id) {
		this.id = id;
	}
	
//	public List<Emp> getRowss() {
//		return rowss;
//	}

//	public void setEmpInfoService(EmpInfoService empInfoService) {
//		this.empInfoService = empInfoService;
//	}
	public long getTotal() {
		return total;
	}

	public List<Emp> getRows() {
		return rows;
	}

	public ControllerResult getResult() {
		return result;
	}

//	public List<EmpInfo> getRows() {
//		return rows;
//	}
	
	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}

	public List<ComboBox4EasyUI> getCobox() {
		return cobox;
	}
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}
	
	public String empSave(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = EmpAction.class.getName() + ".empSave";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			if (empService.queryByEmail(emp)) {
				result = ControllerResult.setFailResult("邮箱已存在，请重新输入");
			} else {
				emp.setStatus(1);
				String pwd = EncryptUtil.encrypt("123456");
				emp.setPwd(pwd);
				empService.save(emp);
				result = ControllerResult.setSuccessResult("添加员工成功");
			}
		} else {
			result = ControllerResult.setFailResult("抱歉，您的权限不足");
		}
		
		return "empSave";
	}
	
//	public String empPage(){
//		Pager4EasyUI<EmpInfo> pager = new Pager4EasyUI<EmpInfo>();
//		pager.setPageNo(WebUtil.getPageNo(req));
//		pager.setPageSize(WebUtil.getPageSize(req));
//		pager = empInfoService.queryByPager(pager);
//		pager.setTotal(empInfoService.count());
//		rows = pager.getRows();
//		total = pager.getTotal();
//		return "pager";
//	}
	
	public String empPage(){
		Pager4EasyUI<Emp> pager = new Pager4EasyUI<Emp>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = empService.queryByPager("Emp", pager);
		pager.setTotal(empService.count("Emp"));
		rows = pager.getRows();
		total = pager.getTotal();
		return "emp_page";
	}
	
	public String empPager(){
		String roleId = WebUtil.getRoleId(req);
		String empId = WebUtil.getEmp(req).getEmpId();
		String methodName = EmpAction.class.getName() + ".empPager";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Pager4EasyUI<Emp> pager = new Pager4EasyUI<Emp>();
			pager.setPageNo(WebUtil.getPageNo(req));
			pager.setPageSize(WebUtil.getPageSize(req));
			pager = empService.queryByPagerEmpId(pager, empId);
			rows = pager.getRows();
			total = pager.getTotal();
		} else {
			result = ControllerResult.setFailResult("抱歉，您的权限不足");
		}
		return "empPage";
	}
	public String empUpdate(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = EmpAction.class.getName() + ".empUpdate";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			System.out.println(emp.getEmpId());
			empService.update(emp);
			result = ControllerResult.setSuccessResult("更新员工成功");
		} else {
			result = ControllerResult.setFailResult("抱歉，您的权限不足");
		}
		
		return "update";
	}
	
	public String empInformat(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = EmpAction.class.getName() + ".empInformat";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			empService.update(emp);
			result = ControllerResult.setSuccessResult("更新信息成功");
		} else {
			result = ControllerResult.setFailResult("抱歉，您的权限不足"); 
		}
		
		return "empInfo";
	}
	
	public String fuzzySearch(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = EmpAction.class.getName() + ".fuzzySearch";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Pager4EasyUI<Emp> pager = new Pager4EasyUI<Emp>();
			pager.setPageNo(WebUtil.getPageNo(req));
			pager.setPageSize(WebUtil.getPageSize(req));
			String name = req.getParameter("name");
			System.out.println(name);
			pager = empService.queryByName(pager, name, "Emp");
			pager.setTotal(empService.count("Emp"));
			rows = pager.getRows();
			total = pager.getTotal();
		} else {
			result = ControllerResult.setFailResult("抱歉，您的权限不足");
		}
		
		return "empName";
	}
	
	public String frost(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = EmpAction.class.getName() + ".frost";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Emp emp = empService.queryById(Emp.class, id);
			if (emp.getStatus() == 1) {
				result = ControllerResult.setSuccessResult("冻结成功");
				empService.updateStatus("Emp", "empId", 0, id);
			} else {
				result = ControllerResult.setFailResult("已经被冻结了，不能再冻结");
			}
		} else {
			result = ControllerResult.setFailResult("抱歉，您的权限不足");
		}
	
		return "id";
	}
	
	public String activation(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = EmpAction.class.getName() + ".activation";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Emp emp = empService.queryById(Emp.class, id);
			if (emp.getStatus() == 0) {
				result = ControllerResult.setSuccessResult("激活成功");
				empService.updateStatus("Emp", "empId", 1, id);
			} else {
				result = ControllerResult.setFailResult("已经被激活了，不能再激活");
			}
		} else {
			result = ControllerResult.setFailResult("抱歉，您的权限不足");
		}
		
		return "id";
	}
	
	public String query_type() {
		List<Emp> emps = empService.queryType(1);
		cobox = new ArrayList<ComboBox4EasyUI>();
		for(Emp p : emps) {
			ComboBox4EasyUI co = new ComboBox4EasyUI();
			co.setId(p.getEmpId());
			co.setText(p.getName());
			cobox.add(co);
		}
		return "query_type";
	}
	
	public String empIdByPager() {
		Pager4EasyUI<Emp> pager = new Pager4EasyUI<Emp>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = empService.queryByEmpIdPager(pager,id);
		rows = pager.getRows();
		total = pager.getTotal();
		return "empIdByPager";
	}
	
	public String queryEmpByRoleId() {
		Pager4EasyUI<Emp> pager = new Pager4EasyUI<Emp>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		String roleId = "";
		if (check == 1) {
			roleId = roleService.queryRoleIdByName("班主任");
		} else if (check == 2) {
			roleId = roleService.queryRoleIdByName("任课老师");
		} else if (check == 3) {
			roleId = roleService.queryRoleIdByName("辅导老师");
		}
		pager = empService.queryEmpByRoleId(pager, roleId);
		pager.setTotal(empService.empRoleIdCount(roleId));
		rows = pager.getRows();
		total = pager.getTotal();
		return "pager";
	}

}
