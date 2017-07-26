package com.ht.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import com.ht.bean.Stu;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.AbstractBaseDAO;
import com.ht.dao.StuDAO;

public class StuDAOImpl extends AbstractBaseDAO<Stu> implements StuDAO {

	@Override
	public Pager4EasyUI<Stu> queryByGradeIdPager(Pager4EasyUI<Stu> pager, Serializable gradeId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Stu where gradeId = ?");
		query.setParameter(0, gradeId);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Stu> list = query.list();
		pager.setRows(list);
		return pager;
	}
	
	@Override
	public Pager4EasyUI<Stu> queryByGradePager(Pager4EasyUI<Stu> pager, Serializable gradeId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Stu where gradeId = ? and roomId = null");
		query.setParameter(0, gradeId);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Stu> list = query.list();
		pager.setRows(list);
		return pager;
	}
	
	@Override
	public long gradeCount(Serializable gradeId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Stu where gradeId = ?");
		query.setParameter(0, gradeId);
		long count = (long) query.uniqueResult();
		return count;
	}
	
	@Override
	public long gradeCount1(Serializable gradeId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Stu where gradeId = ? and roomId = null");
		query.setParameter(0, gradeId);
		long count = (long) query.uniqueResult();
		return count;
	}

	@Override
	public Pager4EasyUI<Stu> stuStatusByPager(Pager4EasyUI<Stu> pager, String stuStatus) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Stu where stuStatus = ?");
		query.setParameter(0, stuStatus);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Stu> list = query.list();
		pager.setRows(list);
		return pager;
	}
	
	@Override
	public Pager4EasyUI<Stu> stuStatusByPager1(Pager4EasyUI<Stu> pager, String stuStatus) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Stu where stuStatus = ? and gradeId = null");
		query.setParameter(0, stuStatus);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Stu> list = query.list();
		pager.setRows(list);
		return pager;
	}
	
	@Override
	public Pager4EasyUI<Stu> queryByRoomIdPager(Pager4EasyUI<Stu> pager, Serializable roomId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Stu where roomId = ?");
		query.setParameter(0, roomId);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Stu> list = query.list();
		pager.setRows(list);
		return pager;
	}

	@Override
	public void addStusToGrade(String gradeId, String[] stuIds) {
		Session session = sessionFactory.getCurrentSession();
		session.doWork(new Work() {

			@Override
			public void execute(Connection conn) throws SQLException {
				Statement stmt = conn.createStatement();
				for (int i = 0, len = stuIds.length; i < len; i++) {
					stmt.addBatch("update t_stu set gradeid = '" + gradeId + "' where stuid = '" + stuIds[i] + "'");
				}
				stmt.executeBatch();
			}
		});
	}

	@Override
	public void addStusToRoom(String roomId, String[] stuIds) {
		Session session = sessionFactory.getCurrentSession();
		session.doWork(new Work () {

			@Override
			public void execute(Connection conn) throws SQLException {
				Statement stmt = conn.createStatement();
				for(int i = 0, len = stuIds.length; i < len; i++) {
					stmt.addBatch("update t_stu set roomid = '" + roomId + "' where stuid = '" + stuIds[i] + "'");
				}
				stmt.executeBatch();
			}
			
		});
	}

	@Override
	public long stuStatusCount(String stuStatus) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Stu where stuStatus = ?"); 
		query.setParameter(0, stuStatus);
		long count = (long) query.uniqueResult();
		return count;
	}
	
	@Override
	public long stuStatusCount1(String stuStatus) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Stu where stuStatus = ? and gradeId = null"); 
		query.setParameter(0, stuStatus);
		long count = (long) query.uniqueResult();
		return count;
	}

	@Override
	public List<Stu> queryType(int status) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select p.stuid,p.name from t_stu p where p.status = "+status);
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		List<Stu> pts = new ArrayList<Stu>();
		for(Object[] obj : list) {
			Stu pt = new Stu();
			pt.setStuId((String) obj[0]);
			pt.setName((String) obj[1]);
			pts.add(pt);
		}
		return pts;
	}

	@Override
	public Pager4EasyUI<Stu> queryByStuName(Pager4EasyUI<Stu> pager, String stuName, String stuStatus) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Stu where name like ? and stuStatus = ?");
		query.setParameter(0, "%" + stuName + "%");
		query.setParameter(1, stuStatus);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Stu> list = query.list();
		pager.setRows(list);
		return pager;
	}

	@Override
	public void updateStuStatus(Stu stu) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "update t_stu set stustatus = ?, startday = ?{, stuno = ?, pwd = ?, role = ?} where stuid = ?";
		if (stu.getStuNo() != null) {
			sql = sql.replace("{, stuno = ?, pwd = ?, role = ?}", ",stuno = ?,pwd = ?, role = ?");
		} else {
			sql = sql.replace("{, stuno = ?, pwd = ?, role = ?}", "");
		}
		SQLQuery query = session.createSQLQuery(sql);
		if (stu.getStuNo() != null) {
			query.setParameter(0, stu.getStuStatus());
			query.setParameter(1, stu.getStartDay());
			query.setParameter(2, stu.getStuNo());
			query.setParameter(3, stu.getPwd());
			query.setParameter(4, stu.getRole());
			query.setParameter(5, stu.getStuId());
		} else {
			query.setParameter(0, stu.getStuStatus());
			query.setParameter(1, stu.getStartDay());
			query.setParameter(2, stu.getStuId());
		}
		query.executeUpdate();
	}

	@Override
	public int getRoomStuCount(String roomId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select count(*) from t_stu where roomid = ?"); // 查询指定宿舍下已经有多少人
		query.setParameter(0, roomId);
		return ((BigInteger) query.uniqueResult()).intValue();
	}

	@Override
	public int getGradeStuCount(String gradeId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select count(*) from t_stu where gradeid = ?"); // 查询指定班级下已经有多少人
		query.setParameter(0, gradeId);
		return ((BigInteger) query.uniqueResult()).intValue();
	}
	
	@Override
	public String getMaxStuNo() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select max(stuno) from t_stu");
		return query.uniqueResult() != null ? (String) query.uniqueResult() : null;
	}

	@Override
	public Pager4EasyUI<Stu> queryBySelf(Pager4EasyUI<Stu> pager, String stuStatus, String empId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Stu s where s.stuStatus = ? and s.emp.empId = ?");
		query.setParameter(0, stuStatus);
		query.setParameter(1, empId);
		query.setFirstResult(pager.getBeginIndex());
		query.setMaxResults(pager.getPageSize());
		@SuppressWarnings("unchecked")
		List<Stu> list = query.list();
		pager.setRows(list);
		return pager;
	}

	@Override
	public long stuStatusCount(String stuStatus, String empId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Stu s where s.stuStatus = ? and s.emp.empId = ?"); 
		query.setParameter(0, stuStatus);
		query.setParameter(1, empId);
		long count = (long) query.uniqueResult();
		return count;
	}

	@Override
	public Stu queryByLogin(Stu stu) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Stu s where s.phone =:sphone and s.pwd =:spwd");
		query.setParameter("sphone", stu.getPhone());
		query.setParameter("spwd", stu.getPwd());
		stu = (Stu) query.uniqueResult();
		if (stu != null) {
			return stu;
		}
		return null;
	}
	
}
