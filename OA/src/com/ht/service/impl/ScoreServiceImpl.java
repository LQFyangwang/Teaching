package com.ht.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ht.bean.Score;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.ScoreDAO;
import com.ht.service.ScoreService;

public class ScoreServiceImpl implements ScoreService {

	private ScoreDAO scoreDAO;
	
	public ScoreDAO getScoreDAO() {
		return scoreDAO;
	}

	public void setScoreDAO(ScoreDAO scoreDAO) {
		this.scoreDAO = scoreDAO;
	}

	@Override
	public void save(Score t) {
		scoreDAO.save(t);
	}

	@Override
	public void delete(Score t) {
		scoreDAO.delete(t);
	}

	@Override
	public void update(Score t) {
		scoreDAO.update(t);
	}

	@Override
	public Score queryById(Class<?> clazz, Serializable s) {
		return scoreDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<Score> queryByPager(String beanName, Pager4EasyUI<Score> pager) {
		pager = scoreDAO.queryByPager(beanName, pager);
		pager.setTotal(scoreDAO.count(beanName));
		return pager;
	}

	@Override
	public List<Score> queryAll(String beanName) {
		return scoreDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return scoreDAO.count(beanName);
	}

	@Override
	public void updateStatus(String beanName, String idName, int status, String id) {
		scoreDAO.updateStatus(beanName, idName, status, id);
	}

	@Override
	public Pager4EasyUI<Score> queryPagerByGradeId(Pager4EasyUI<Score> pager, Serializable gradeId,
			Serializable courseId) {
		return scoreDAO.queryPagerByGradeId(pager, gradeId, courseId);
	}

	@Override
	public Pager4EasyUI<Score> queryByStuName(Pager4EasyUI<Score> pager, Serializable stuName,
			Serializable beanObject) {
		return scoreDAO.queryByStuName(pager, stuName, beanObject);
	}

	@Override
	public Pager4EasyUI<Score> queryByDay(Pager4EasyUI<Score> pager, Serializable startDay, Serializable endDay,
			Serializable beanObject, Serializable attrName) {
		return scoreDAO.queryByDay(pager, startDay, endDay, beanObject, attrName);
	}

	@Override
	public Pager4EasyUI<Score> queryByEmpName(Pager4EasyUI<Score> pager, Serializable empName,
			Serializable beanObject) {
		return scoreDAO.queryByEmpName(pager, empName, beanObject);
	}

	@Override
	public Pager4EasyUI<Score> queryByStuId(Pager4EasyUI<Score> pager, Serializable stuId) {
		pager = scoreDAO.queryByStuId(pager, stuId);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

	@Override
	public Pager4EasyUI<Score> queryByDayAndStuId(Pager4EasyUI<Score> pager, Serializable stuId, String startDay,
			String endDay) {
		pager = scoreDAO.queryByDayAndStuId(pager, stuId, startDay, endDay);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

}
