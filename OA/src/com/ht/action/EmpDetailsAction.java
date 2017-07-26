package com.ht.action;

import com.ht.bean.Emp;
import com.ht.common.DateUtil;

public class EmpDetailsAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Emp emp;
	private String empStr;

	public Emp getEmp() {
		return emp;
	}
	
	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public void setEmpStr(String empStr) {
		this.empStr = empStr;
	}

	public String empDetails() {
		emp = new Emp();
		String[] empStrs = empStr.split(",");
		emp.setBirthday(DateUtil.toDate(empStrs[0]));
		emp.setPhone(empStrs[1]);
		emp.setQq(empStrs[2]);
		emp.setIdCard(empStrs[3]);
		emp.setWechat(empStrs[4]);
		emp.setAddress(empStrs[5]);
		emp.setMarried(empStrs[6]);
		emp.setContactName(empStrs[7]);
		emp.setContactPhone(empStrs[8]);
		emp.setBankName(empStrs[9]);
		emp.setAccountName(empStrs[10]);
		emp.setAccountNo(empStrs[11]);
		emp.setAlipay(empStrs[12]);
		emp.setResignDay((DateUtil.toDate(empStrs[13])));
		return "empDetails";
	}
}
