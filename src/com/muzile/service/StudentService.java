package com.muzile.service;

import java.util.List;


import com.muzile.bean.Result;
import com.muzile.bean.Score;
import com.muzile.bean.User;
import com.muzile.dao.StudentDao;

public class StudentService {
	StudentDao dao = new StudentDao();
	public List<Result> resList(User user){
		
		return dao.resList(user);
	}
	
	public List<Result> getListByDate(User user,Score score,Boolean isOrderByScore){
		return dao.getListByDate(user, score,isOrderByScore);
	}
	
	public int getMaxDate(User user){
		return dao.getMaxDate(user);
	}
	
	public double getAverageScore(User user,Score score){
		return dao.getAverageScore(user, score);
	}
	
	public double getPassRate(User user){
		return dao.getPassRate(user);
	}
}
