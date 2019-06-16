package com.muzile.service;

import java.sql.SQLException;
import java.util.List;

import com.muzile.bean.Result;
import com.muzile.bean.Score;
import com.muzile.bean.Subject;
import com.muzile.bean.User;
import com.muzile.dao.TeacherDao;
import com.muzile.dao.UserDao;
import com.muzile.utils.DataSourceUtils;

public class TeacherService {
	private UserDao userDao = new UserDao();
	private TeacherDao teacherDao = new TeacherDao();
	
	public List<User> getUserList4Teacher(){
		return userDao.getList4Teacher();
	}
	
	public List<User> getStuNameList(){
		return userDao.getStuNameList();
	}
	
	public List<Result> getStuScore(User user, Score score){
		return teacherDao.getStuScore(user, score);
	}

	public void update(List<Result> list) {
		try {
			DataSourceUtils.startTransaction();
			for(Result res : list){
				teacherDao.update(res);
			}
			DataSourceUtils.commitAndRelease();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	public void delete(int id) {
		try {
			DataSourceUtils.startTransaction();
			teacherDao.delete(id);
			DataSourceUtils.commitAndRelease();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public List<Subject> getSubjects(){
		return teacherDao.getSubjects();
	}

	public void add(Score score) {
		try {
			DataSourceUtils.startTransaction();
			teacherDao.add(score);
			DataSourceUtils.commitAndRelease();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public boolean isExist(Score score){
		return teacherDao.isExist(score);
	}
	
	public List<User> getAnalysis4Rank(Score score){
		return teacherDao.getAnalysis4Rank(score);
	}
	
	public double getPassRate(Score score){
		return teacherDao.getPassRate(score);
	}
}
