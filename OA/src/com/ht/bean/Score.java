package com.ht.bean;

import java.util.Date;

/**
 * 学生考试成绩表
 * @author Asa
 *
 */
public class Score {

	private String scoreId; //成绩编号
	private float score; //成绩
	private Date testDay; //考试时间
	
	private Stu stu; // 学生
	private Course course; // 课程
	
	public String getScoreId() {
		return scoreId;
	}
	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public Date getTestDay() {
		return testDay;
	}
	public void setTestDay(Date testDay) {
		this.testDay = testDay;
	}
	public Stu getStu() {
		return stu;
	}
	public void setStu(Stu stu) {
		this.stu = stu;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
}
