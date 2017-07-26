package com.ht.bean.info;

public class EmpSalaryInfo {

	private String empId; // 员工编号	(关联到t_emp表)
	private String deptName; // 部门	(关联到t_dept表)
	private String roleName; // 角色	(关联到t_role表)
	private String name; // 员工姓名	(关联到t_emp表)
	private String idCard; // 身份证号	(关联到t_emp表)
	private String gender; // 性别	(关联到t_emp表)
	private String bankName; // 开户行名称	(关联到t_emp表)
	private String accountName; // 银行卡姓名	(关联到t_emp表)
	private String accountNo; // 银行账号	(关联到t_emp表)
	private double basicSalary; // 基本工资	(关联到t_salaryinfo表)
	private double jobSalary; // 岗位工资	(关联到t_salaryinfo表)
	private String salaryinfoId;
	
	
	public String getSalaryinfoId() {
		return salaryinfoId;
	}
	public void setSalaryinfoId(String salaryinfoId) {
		this.salaryinfoId = salaryinfoId;
	}
	public double getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}
	public double getJobSalary() {
		return jobSalary;
	}
	public void setJobSalary(double jobSalary) {
		this.jobSalary = jobSalary;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
}
