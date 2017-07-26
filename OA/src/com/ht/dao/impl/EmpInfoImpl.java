package com.ht.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import com.ht.bean.Emp;
import com.ht.bean.info.EmpInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.EmpInfoDAO;

public class EmpInfoImpl extends AbstractBaseDAO<EmpInfo> implements EmpInfoDAO {

	@Override
	public Pager4EasyUI<EmpInfo> queryByPager(Pager4EasyUI<EmpInfo> pager) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select e.empid,e.name,e.pwd,e.idcard,e.nation,e.gender,e.fingerno,e.birthday,e.email,e.phone,e.qq,e.wechat,e.address,e.married,e.contactname,e.contactphone,e.college,e.major,e.eduback,e.bankname,e.accountno,e.alipay,e.hireday,e.resignday,e.status,d.name as name1,r.name as name2,e.depId,e.roleId from t_dept d, t_role r, t_emp e where e.depid = d.depid and e.roleid = r.roleid");
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list(); // 每一个Object[]存放的是每一行记录的所有字段
		List<EmpInfo> info = new ArrayList<EmpInfo>();
		for (Object[] objs : list) {
			EmpInfo eminfo = new EmpInfo();
			eminfo.setEmpId((String) objs[0]);
			eminfo.setName((String) objs[1]);
			eminfo.setPwd((String) objs[2]);
			eminfo.setIdCard((String) objs[3]);
			eminfo.setNation((String) objs[4]);
			eminfo.setGender((String) objs[5]);
			eminfo.setFingerNo((String) objs[6]);
			eminfo.setBirthday((Date) objs[7]);
			eminfo.setEmail((String) objs[8]);
			eminfo.setPhone((String) objs[9]);
			eminfo.setQq((String) objs[10]);
			eminfo.setWechat((String) objs[11]);
			eminfo.setAddress((String) objs[12]);
			eminfo.setMarried((String) objs[13]);
			eminfo.setContactName((String) objs[14]);
			eminfo.setContactPhone((String) objs[15]);
			eminfo.setCollege((String) objs[16]);
			eminfo.setMajor((String) objs[17]);
			eminfo.setEduBack((String) objs[18]);
			eminfo.setBankName((String) objs[19]);
			eminfo.setAccountNo((String) objs[20]);
			eminfo.setAlipay((String) objs[21]);
			eminfo.setHireDay((Date) objs[22]);
			eminfo.setResignDay((Date) objs[23]);
			eminfo.setStatus((int)objs[24]);
			eminfo.setDepName((String) objs[25]);
			eminfo.setRoleName((String) objs[26]);
			eminfo.setDepId((String) objs[27]);
			eminfo.setRoleId((String) objs[28]);
			info.add(eminfo);
		}
		pager.setRows(info);
		return pager;
	}

	@Override
	public long count() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Emp.class);
		long count = (long) criteria.setProjection(Projections.count("empId")).uniqueResult();
		session.close();
		return count;
	}

}
