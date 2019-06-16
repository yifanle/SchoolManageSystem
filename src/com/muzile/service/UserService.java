package com.muzile.service;

import java.sql.SQLException;

import com.muzile.bean.User;
import com.muzile.dao.UserDao;
import com.muzile.utils.DataSourceUtils;

public class UserService {
	
	private UserDao dao = new UserDao();
	
	public User login(User user){
		return dao.login(user);
	}
	
	public boolean regist(User user){
		try {
			DataSourceUtils.startTransaction();
			dao.regist(user);
			DataSourceUtils.commitAndRelease();
			return true;
		} catch (SQLException e) {
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}finally{
			try {
				DataSourceUtils.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
