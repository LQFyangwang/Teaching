package com.ht.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.ht.bean.Emp;
import com.ht.bean.Income;
import com.ht.bean.IncomeType;
import com.ht.bean.Stu;
import com.ht.common.DateUtil;
import com.ht.common.EncryptUtil;
import com.ht.common.bean.ComboBox4EasyUI;
import com.ht.common.bean.ControllerResult;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.common.web.WebUtil;
import com.ht.service.AuthorityRoleService;
import com.ht.service.IncomeService;
import com.ht.service.IncomeTypeService;
import com.ht.service.StuService;

public class StuAction extends BaseAction {

	private static final long serialVersionUID = -8815037733870248460L;
	
	private StuService stuService;
	private Stu stu;
	private List<Stu> rows;
	private long total;
	private String gradeId;
	private String roomId;
	private ControllerResult result;
	private String id;
	private String stuIds;
	private List<ComboBox4EasyUI> cobox;
	private String name;
	private String value;
	private String stuId;
	private int flag;
	private IncomeService incomeService;
	private Income income;
	private double r; //缴费金额
	private IncomeType incomeType;
	private HttpSession session;
	private IncomeTypeService itService;
	private String incomeTypeId; // 收入类型Id
	
	private String stuEmpId; // 学生属于哪个 员工 ，从jsp传递过来
	private String stuStatus; //学生状态，从jsp传递
	
	private int gradeTotalStu;
	private int roomTotalStu;
	private String abcdef;
	private AuthorityRoleService authorityRoleService;
	
	public void setAbcdef(String abcdef) {
		this.abcdef = abcdef;
	}

	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}

	public void setItService(IncomeTypeService itService) {
		this.itService = itService;
	}

	public IncomeType getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(IncomeType incomeType) {
		this.incomeType = incomeType;
	}

	public Income getIncome() {
		return income;
	}

	public void setIncome(Income income) {
		this.income = income;
	}

	public void setIncomeService(IncomeService incomeService) {
		this.incomeService = incomeService;
	}

	public List<ComboBox4EasyUI> getCobox() {
		return cobox;
	}

	public Stu getStu() {
		return stu;
	}

	public void setStu(Stu stu) {
		this.stu = stu;
	}

	public List<Stu> getRows() {
		return rows;
	}

	public long getTotal() {
		return total;
	}

	public void setStuService(StuService stuService) {
		this.stuService = stuService;
	}
	
	public void setStuIds(String stuIds) {
		this.stuIds = stuIds;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public ControllerResult getResult() {
		return result;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public void setR(double r) {
		this.r = r;
	}

	public void setGradeTotalStu(int gradeTotalStu) {
		this.gradeTotalStu = gradeTotalStu;
	}

	public void setRoomTotalStu(int roomTotalStu) {
		this.roomTotalStu = roomTotalStu;
	}

	public void setStuEmpId(String stuEmpId) {
		this.stuEmpId = stuEmpId;
	}

	public void setStuStatus(String stuStatus) {
		this.stuStatus = stuStatus;
	}

	public String gradeIdByPager() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuAction.class.getName() + ".gradeIdByPager";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Pager4EasyUI<Stu> pager = new Pager4EasyUI<Stu>();
			pager.setPageNo(WebUtil.getPageNo(req));
			pager.setPageSize(WebUtil.getPageSize(req));
			pager = stuService.queryByGradeIdPager(pager, gradeId);
			rows = pager.getRows();
			total = pager.getTotal();
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "gradeByPager";
	}
	
	public String gradeByPager() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuAction.class.getName() + ".gradeByPager";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Pager4EasyUI<Stu> pager = new Pager4EasyUI<Stu>();
			pager.setPageNo(WebUtil.getPageNo(req));
			pager.setPageSize(WebUtil.getPageSize(req));
			pager = stuService.queryByGradePager(pager, gradeId);
			rows = pager.getRows();
			total = pager.getTotal();
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "gradeByPager";
	}
	
	public String roomByPager() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuAction.class.getName() + ".roomByPager";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Pager4EasyUI<Stu> pager = new Pager4EasyUI<Stu>();
			pager.setPageNo(WebUtil.getPageNo(req));
			pager.setPageSize(WebUtil.getPageSize(req));
			pager = stuService.queryByRoomIdPager(pager, roomId);
			rows = pager.getRows();
			total = pager.getTotal();
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "roomByPager";
	}
	
	public String intentionStuByPager() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuAction.class.getName() + ".intentionStuByPager";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			String role = WebUtil.getRole(req);
			Emp emp = WebUtil.getEmp(req);
			Pager4EasyUI<Stu> pager = new Pager4EasyUI<Stu>();
			pager.setPageNo(WebUtil.getPageNo(req));
			pager.setPageSize(WebUtil.getPageSize(req));
			if (role.equals("总经理") || role.equals("招生主任")) {
				pager = stuService.stuStatusByPager(pager, "intention");
				pager.setTotal(stuService.stuStatusCount("intention"));
			} else {
				pager = stuService.queryBySelf(pager, "intention", emp.getEmpId());
				pager.setTotal(stuService.stuStatusCount("intention", emp.getEmpId()));
			}
			rows = pager.getRows();
			total = pager.getTotal();
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "stuStatusByPager";
	}

	public String reserveStuByPager() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuAction.class.getName() + ".reserveStuByPager";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			String role = WebUtil.getRole(req);
			Emp emp = WebUtil.getEmp(req);
			Pager4EasyUI<Stu> pager = new Pager4EasyUI<Stu>();
			pager.setPageNo(WebUtil.getPageNo(req));
			pager.setPageSize(WebUtil.getPageSize(req));
			if (role.equals("总经理") || role.equals("招生主任")) {
				pager = stuService.stuStatusByPager(pager, "reserve");
				pager.setTotal(stuService.stuStatusCount("reserve"));
			} else {
				pager = stuService.queryBySelf(pager, "reserve", emp.getEmpId());
				pager.setTotal(stuService.stuStatusCount("reserve", emp.getEmpId()));
			}
			rows = pager.getRows();
			total = pager.getTotal();
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "stuStatusByPager";
	}
	
	public String officialStuByPager() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuAction.class.getName() + ".officialStuByPager";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Pager4EasyUI<Stu> pager = new Pager4EasyUI<Stu>();
			pager.setPageNo(WebUtil.getPageNo(req));
			pager.setPageSize(WebUtil.getPageSize(req));
			pager = stuService.stuStatusByPager(pager, "official");
			pager.setTotal(stuService.stuStatusCount("official"));
			rows = pager.getRows();
			total = pager.getTotal();
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "stuStatusByPager";
	}
	
	public String officialStuByPager1() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuAction.class.getName() + ".officialStuByPager1";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Pager4EasyUI<Stu> pager = new Pager4EasyUI<Stu>();
			pager.setPageNo(WebUtil.getPageNo(req));
			pager.setPageSize(WebUtil.getPageSize(req));
			pager = stuService.stuStatusByPager1(pager, "official");
			pager.setTotal(stuService.stuStatusCount1("official"));
			rows = pager.getRows();
			total = pager.getTotal();
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "stuStatusByPager";
	}
	
	public String save() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuAction.class.getName() + ".save";
		session = req.getSession();
		Emp emp = new Emp();
		Emp e = (Emp) session.getAttribute("emp");
		emp.setEmpId(e.getEmpId());
		if (flag == 1) {
			if (authorityRoleService.queryByRole(methodName+"?flag=1", roleId)) {
				stu.setStuStatus("intention"); // 设置成意向学生
				stu.setEmp(emp);
				stu.setStatus(1);
				try {
					stu.setAge(DateUtil.getAge(stu.getBirthday()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				stu.setRoleId("4028da265981b09d015981d91d6f0001");
				stuService.save(stu);
			} else {
				result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			}
		} else if (flag == 2) {
				// 产生一条缴费记录
			if (authorityRoleService.queryByRole(methodName+"?flag=2", roleId)) {
				income = new Income();
				incomeTypeId = itService.queryByName("预定报名费"); // 获取到预定报名费的Id
				income.setDes("预定报名费"); // 添加缴费描述
				
				IncomeType it = new IncomeType();
				it.setIncomeTypeId(incomeTypeId);;
				income.setIt(it);
				income.setIncomeDay(DateUtil.getDate()); // 缴费时间
				income.setIncome(r); // 缴费金额
				income.setEmp(emp); // 收费人
				stu.setStuStatus("reserve"); // 预定学生
				stu.setEmp(emp);
				stu.setStatus(1);
				try {
					stu.setAge(DateUtil.getAge(stu.getBirthday()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				stu.setRoleId("4028da265981b09d015981d91d6f0001");
				stuService.save(stu);
				String stuId = stu.getStuId();
				Stu s = new Stu();
				s.setStuId(stuId);
				income.setStu(s); // 缴费的学生
				incomeService.save(income); // 产生缴费记录
			} else {
				result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			}
		} else if (flag == 3) {
			if (authorityRoleService.queryByRole(methodName+"?flag=3", roleId)) {
				income = new Income();
				incomeTypeId = itService.queryByName("学费");
				income.setDes("学费");
				IncomeType it = new IncomeType();
				it.setIncomeTypeId(incomeTypeId);;
				income.setIt(it);
				income.setIncomeDay(DateUtil.getDate());
				income.setIncome(r);
				income.setEmp(emp);
				stu.setStuStatus("official"); // 正式学生
				stu.setEmp(emp);
				stu.setStatus(1);
				stu.setPwd(EncryptUtil.encrypt("123456"));
				stu.setRole("学生");
				stu.setRoleId("4028da265981b09d015981d91d6f0001");
				stu.setStuNo(stuService.generateStuNo());
				try {
					stu.setAge(DateUtil.getAge(stu.getBirthday()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				stuService.save(stu);
				String stuId = stu.getStuId();
				Stu s = new Stu();
				s.setStuId(stuId);
				income.setStu(s); // 缴费的学生
				incomeService.save(income); // 产生缴费记录
			} else {
				result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			}
		}
		result = ControllerResult.setSuccessResult("添加成功");
		return "save";
	}
	
	public String update() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuAction.class.getName() + ".update";
		if (flag == 1) {
			if (authorityRoleService.queryByRole(methodName+"?flag=1", roleId)) {
				stu.setStuStatus("intention"); // 设置成意向学生
				stu.setStatus(1);
				try {
					stu.setAge(DateUtil.getAge(stu.getBirthday()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				stu.setRoleId("4028da265981b09d015981d91d6f0001");
				stuService.update(stu);
				result = ControllerResult.setSuccessResult("更新成功");
			} else {
				result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			}
		} else if (flag == 2) {
			if (authorityRoleService.queryByRole(methodName+"?flag=2", roleId)) {
				stu.setStuStatus("reserve"); // 预定学生
				stu.setStatus(1);
				try {
					stu.setAge(DateUtil.getAge(stu.getBirthday()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				stu.setRoleId("4028da265981b09d015981d91d6f0001");
				stuService.update(stu);
				result = ControllerResult.setSuccessResult("更新成功");
			} else {
				result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			}
		} else if (flag == 3) {
			if (authorityRoleService.queryByRole(methodName+"?flag=3", roleId)) {
				stu.setStuStatus("official"); // 正式学生
				stu.setPwd(abcdef);
				stu.setStatus(1);
				if (stu.getGradeId().equals("")) {
					stu.setGradeId(null);
				}
				if (stu.getRoomId().equals("")) {
					stu.setRoomId(null);
				}
				try {
					stu.setAge(DateUtil.getAge(stu.getBirthday()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				stu.setRoleId("4028da265981b09d015981d91d6f0001");
				stuService.update(stu);
				result = ControllerResult.setSuccessResult("更新成功");
			} else {
				result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			}
		}
		
		return "update";
	}
	
	public String updateStuStatus() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuAction.class.getName() + ".updateStuStatus";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			session = req.getSession();
			Emp emp = new Emp();
			Emp e = (Emp) session.getAttribute("emp");
			emp.setEmpId(e.getEmpId());
			if (flag == 2) {
				income = new Income();
				incomeTypeId = itService.queryByName("预定报名费");
				income.setDes("预定报名费");
				IncomeType it = new IncomeType();
				it.setIncomeTypeId(incomeTypeId);
				income.setIt(it);
				income.setIncomeDay(DateUtil.getDate());
				income.setIncome(r);
				income.setEmp(emp);
				stu = new Stu();
				stu.setStuId(stuId);
				income.setStu(stu); // 缴费的学生
				incomeService.save(income); // 产生缴费记录
				stu.setStuStatus("reserve"); // 预定学生
				stu.setRoleId("4028da265981b09d015981d91d6f0001");
				stu.setStartDay(DateUtil.getDate());
			} else if (flag == 3) {
				income = new Income();
				incomeTypeId = itService.queryByName("学费");
				income.setDes("学费");
				IncomeType it = new IncomeType();
				it.setIncomeTypeId(incomeTypeId);;
				income.setIt(it);
				income.setIncomeDay(DateUtil.getDate());
				income.setIncome(r);
				income.setEmp(emp);
				stu = new Stu();
				stu.setStuId(stuId);
				income.setStu(stu); // 缴费的学生
				incomeService.save(income); // 产生缴费记录
				stu.setStuStatus("official"); // 正式学生
				stu.setRole("学生");
				stu.setRoleId("4028da265981b09d015981d91d6f0001");
				stu.setPwd(EncryptUtil.encrypt("123456"));
				stu.setStartDay(DateUtil.getDate());
				stu.setStuNo(stuService.generateStuNo());
			}
			stu.setEmp(emp);
			stuService.updateStuStatus(stu);
			result = ControllerResult.setSuccessResult("更新成功");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "update";
	}
	
	public String id() {
		stu = stuService.queryById(Stu.class, id);
		return "id";
	}
	
	public String all() {
		stuService.queryAll("Stu");
		return "all";
	}
	
	public String pager() {
		Pager4EasyUI<Stu> pager = new Pager4EasyUI<Stu>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = stuService.queryByPager("Stu", pager);
		pager.setTotal(stuService.count("Stu"));
		rows = pager.getRows();
		total = pager.getTotal();
		return "pager";
	}
	
	public String frost() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuAction.class.getName() + ".frost";
		String roleName = WebUtil.getRole(req);
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			if (roleName.equals("总经理") || roleName.equals("校长") || roleName.equals("班主任")
					|| (stuEmpId.equals(WebUtil.getEmp(req).getEmpId()) && !stuStatus.equals("official"))) {
				Stu stu = stuService.queryById(Stu.class, id);
				if (stu.getStatus() == 1) {
					result = ControllerResult.setSuccessResult("冻结成功");
					stuService.updateStatus("Stu", "stuId", 0, id);
				} else {
					result = ControllerResult.setFailResult("已经被冻结了，不能再冻结");
				}
			} else {
				result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			}
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "status";
	}
	
	public String activation() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuAction.class.getName() + ".activation";
		String roleName = WebUtil.getRole(req);
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			if (roleName.equals("总经理") || roleName.equals("校长") || roleName.equals("班主任")
					|| (stuEmpId.equals(WebUtil.getEmp(req).getEmpId()) && !stuStatus.equals("official"))) {
				Stu stu = stuService.queryById(Stu.class, id);
				if (stu.getStatus() == 0) {
					result = ControllerResult.setSuccessResult("激活成功");
					stuService.updateStatus("Stu", "stuId", 1, id);
				} else {
					result = ControllerResult.setFailResult("已经激活了，不能再激活");
				}
			} else {
				result = ControllerResult.setFailResult("抱歉，您没有权限操作");
			}
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "status";
	}
	
	public String addStusToGrade() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuAction.class.getName() + ".addStusToGrade";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			if (stuIds != null) {
				String[] stuIdArray = stuIds.split(",");
				int count = stuService.getGradeStuCount(gradeId); // 班级已经有多少人
				if (gradeTotalStu >= count + stuIdArray.length) {
					stuService.addStusToGrade(gradeId, stuIdArray);
					result = ControllerResult.setSuccessResult("添加学生成功");
				}  else {
					result = ControllerResult.setFailResult("超出学生总数，超出" + (count + stuIdArray.length - gradeTotalStu) + "人");
				}
				
			}
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "addStus";
	}
	
	public String addStusToRoom() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuAction.class.getName() + ".addStusToRoom";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			if (stuIds != null) {
				String[] stuIdArray = stuIds.split(",");
				int count = stuService.getRoomStuCount(roomId); // 班级已经有多少人
				if (roomTotalStu >= count + stuIdArray.length) {
					stuService.addStusToRoom(roomId, stuIdArray);
					result = ControllerResult.setSuccessResult("添加学生成功");
				}  else {
					result = ControllerResult.setFailResult("超出学生总数，超出" + (count + stuIdArray.length - roomTotalStu) + "人");
				}
			}
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "addStus";
	}
	
	public String query_type() {
		List<Stu> emps = stuService.queryType(1);
		cobox = new ArrayList<ComboBox4EasyUI>();
		for(Stu p : emps) {
			ComboBox4EasyUI co = new ComboBox4EasyUI();
			co.setId(p.getStuId());
			co.setText(p.getName());
			String stuid = req.getParameter("stuId");
			if(p.getStuId().equals(stuid)) {
				co.setSelected(true);
			}
			cobox.add(co);
		}
		return "query_type";
	}
	
	public String fuzzySearch1() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuAction.class.getName() + ".fuzzySearch1";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Pager4EasyUI<Stu> pager = new Pager4EasyUI<Stu>();
			pager.setPageNo(WebUtil.getPageNo(req));
			pager.setPageSize(WebUtil.getPageSize(req));
			if (name.equals("stuName")) {
				pager = stuService.queryByStuName(pager, value, "intention");
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
	
	public String fuzzySearch2() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuAction.class.getName() + ".fuzzySearch2";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Pager4EasyUI<Stu> pager = new Pager4EasyUI<Stu>();
			pager.setPageNo(WebUtil.getPageNo(req));
			pager.setPageSize(WebUtil.getPageSize(req));
			if (name.equals("stuName")) {
				pager = stuService.queryByStuName(pager, value, "reserve");
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
	
	public String fuzzySearch3() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuAction.class.getName() + ".fuzzySearch3";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			Pager4EasyUI<Stu> pager = new Pager4EasyUI<Stu>();
			pager.setPageNo(WebUtil.getPageNo(req));
			pager.setPageSize(WebUtil.getPageSize(req));
			if (name.equals("stuName")) {
				pager = stuService.queryByStuName(pager, value, "official");
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
	
	
	public String details() {
		String roleId = WebUtil.getRoleId(req);
		String methodName = StuAction.class.getName() + ".details";
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			stu = stuService.queryById(Stu.class, stuId);
			req.setAttribute("stu", stu);
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "details";
	}

	
}
