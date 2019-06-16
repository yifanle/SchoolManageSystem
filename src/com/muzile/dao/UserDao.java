package com.muzile.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.muzile.bean.User;
import com.muzile.utils.DataSourceUtils;

public class UserDao {
	
	public User login(User loginUser){
		User user = null;
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username = ? and password = ? and role=?";
		Object[] params = {loginUser.getUsername(),loginUser.getPassword(),loginUser.getRole()};
		try {
			user = qr.query(sql,new BeanHandler<User>(User.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public void regist(User registUser) throws SQLException{
		/**
		 * 			user.setName(getNameField().getText());
					user.setNum(getSchoolNum().getText());
					user.setUsername(getUsername().getText());
					user.setPassword(String.valueOf(getPassword().getPassword()));
					user.setSex(sex);
					user.setRole(role);
		 */
		QueryRunner qr = new QueryRunner();
		Connection con = DataSourceUtils.getCurrentConnection();
		String sql = "insert into user(name,username,password,num,sex,role) values(?,?,?,?,?,?)";
		Object[] params = {registUser.getName(),registUser.getUsername(),registUser.getPassword(),registUser.getNum(),registUser.getSex(),registUser.getRole()};
		qr.update(con, sql, params);
	}
	
	public List<User> getList4Teacher(){
		List<User> list = null;
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT *,(SELECT COUNT(*) FROM score s WHERE s.uid = u.uid AND score < 60) fsn FROM USER u WHERE u.role = '学生'";
		try {
			list = qr.query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<User> getStuNameList(){
		List<User> list = null;
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM USER u WHERE u.role='学生'";
		try {
			list = qr.query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
