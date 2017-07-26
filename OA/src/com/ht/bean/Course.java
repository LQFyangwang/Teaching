package com.ht.bean;

import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

/**
 * 对应课程表
 * 
 * @author xiaoqiang
 *
 */
public class Course {

	private String courseId; // 课程编号
	private String name; // 课程名
	private String des; // 描述
	private int totalHour; // 总课时数
	private int term; // 学期
	private int courseOrder; // 课程顺序
	private int status; // 状态，默认为可用
	
	private Set<Score> scores; // 成绩

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public int getTotalHour() {
		return totalHour;
	}

	public void setTotalHour(int totalHour) {
		this.totalHour = totalHour;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public int getCourseOrder() {
		return courseOrder;
	}

	public void setCourseOrder(int courseOrder) {
		this.courseOrder = courseOrder;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	@JSON(serialize=false)
	public Set<Score> getScores() {
		return scores;
	}

	public void setScores(Set<Score> scores) {
		this.scores = scores;
	}

}
