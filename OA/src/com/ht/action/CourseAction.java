package com.ht.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.ht.bean.Course;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.ht.service.AuthorityRoleService;
import com.ht.service.CourseService;

public class CourseAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = -937554594318645021L;
	
	private HttpServletRequest req;
	
	private Course course;
	private CourseService courseService;
	private AuthorityRoleService authorityRoleService;
	private List<Course> rows;
	private long total;
	private ControllerResult result;
	private String id;
	private String name;
	private String value;
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Course> getRows() {
		return rows;
	}

	public long getTotal() {
		return total;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	
	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}
	
	public String pager(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = CourseAction.class.getName() + ".pager";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "pager";
		}
		Pager4EasyUI<Course> pager = new Pager4EasyUI<Course>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = courseService.queryByPager("Course", pager);
		rows = pager.getRows();
		total = pager.getTotal();
		result = ControllerResult.setSuccessResult("查询成功");
		return "pager";
	}
	
	public String add(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = CourseAction.class.getName() + ".add";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "add";
		}
		course.setStatus(1);
		courseService.save(course);
		result = ControllerResult.setSuccessResult("添加成功");
		return "add";
	}
	
	public String update(){
		String roleId = WebUtil.getRoleId(req);
		String methodName = CourseAction.class.getName() + ".update";
		boolean f = authorityRoleService.queryByRole(methodName, roleId);
		System.out.println("roleId:" + roleId + ", methodName:" + methodName + ", f:" + f);
		if (f == false) {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
			return "update";
		}
		courseService.update(course);
		result = ControllerResult.setSuccessResult("更新成功");
		return "update";
	}
	
	public String frost() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = CourseAction.class.getName() + ".frost";
		Course course = courseService.queryById(Course.class, id);
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			if (course.getStatus() == 1) {
				result = ControllerResult.setSuccessResult("冻结成功");
				courseService.updateStatus("Course", "courseId", 0, id);
			} else {
				result = ControllerResult.setFailResult("已经被冻结了，不能再冻结");
			}
		} else {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
		}
		return "status";
	}
	
	public String activation() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = CourseAction.class.getName() + ".activation";
		Course course = courseService.queryById(Course.class, id);
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			if (course.getStatus() == 0) {
				result = ControllerResult.setSuccessResult("激活成功");
				courseService.updateStatus("Course", "courseId", 1, id);
			} else {
				result = ControllerResult.setFailResult("已经激活了，不能再激活");
			}
		} else {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
		}
		return "status";
	}
	
	public ControllerResult getResult() {
		return result;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String fuzzySearch() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = CourseAction.class.getName() + ".fuzzySearch";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Pager4EasyUI<Course> pager = new Pager4EasyUI<Course>();
			pager.setPageNo(WebUtil.getPageNo(req));
			pager.setPageSize(WebUtil.getPageSize(req));
			if (name.equals("courseName")) {
				pager = courseService.queryByCourseName(pager, value);
			}
			rows = pager.getRows();
			total = pager.getTotal();
			if (rows != null) {
				result = ControllerResult.setSuccessResult("查找成功");
			} else {
				result = ControllerResult.setFailResult("没有记录");
			}
		} else {
			result = ControllerResult.setFailResult("抱歉,您没有权限操作");
		}
		return "fuzzySearch";
	}

}
