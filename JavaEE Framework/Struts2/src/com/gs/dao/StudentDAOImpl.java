package com.gs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.Student;

public class StudentDAOImpl extends BaseDAO implements StudentDAO {

	@Override
	public Student save(Student stu) {
		getConn();
		String sql = "insert into t_student(id, email, gender, des) values(uuid(),?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, stu.getEmail());
			ps.setString(2, stu.getGender());
			ps.setString(3, stu.getDes());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return stu;
	}

	@Override
	public void update(Student stu) {
		getConn();
		String sql = "update t_student set email = ?, gender = ?, des = ? where id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, stu.getEmail());
			ps.setString(2, stu.getGender());
			ps.setString(3, stu.getDes());
			ps.setString(4, stu.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public Student show(String id) {
		Student stu = null;
		getConn();
		String sql = "select * from t_student where id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				stu = new Student();
				stu.setId(id);
				stu.setEmail(rs.getString("email"));
				stu.setGender(rs.getString("gender"));
				stu.setDes(rs.getString("des"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return stu;
	}

	@Override
	public List<Student> all() {
		List<Student> stus = new ArrayList<Student>();
		getConn();
		String sql = "select * from t_student";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getString("id"));
				stu.setEmail(rs.getString("email"));
				stu.setGender(rs.getString("gender"));
				stu.setDes(rs.getString("des"));
				stus.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return stus;
	}

}
