package com.muzile.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.muzile.bean.Result;
import com.muzile.bean.Score;
import com.muzile.bean.User;
import com.muzile.utils.DataSourceUtils;

public class StudentDao {
	public List<Result> resList(User user){
		List<Result> list = null;
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM score s LEFT OUTER JOIN SUBJECT sb ON s.suid = sb.suid WHERE s.uid = ? ORDER BY sid DESC";
		try {
			 list = qr.query(sql, new BeanListHandler<Result>(Result.class), user.getUid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Result> getListByDate(User user,Score score,Boolean isOrderByScore){
		List<Result> list = null;
		String sql = "";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		if(!isOrderByScore){
			sql = "SELECT * FROM score s LEFT OUTER JOIN SUBJECT sb ON s.suid = sb.suid WHERE s.uid = ? and s.date = ? ORDER BY sid asc";
		}else{
			sql = "SELECT * FROM score s LEFT OUTER JOIN SUBJECT sb ON s.suid = sb.suid WHERE s.uid = ? and s.date = ? ORDER BY s.score DESC";
		}
		try {
			list = qr.query(sql, new BeanListHandler<Result>(Result.class), user.getUid(),score.getDate());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public int getMaxDate(User user){
		int result = -1;
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT MAX(s.flag) FROM score s WHERE s.uid = ?";
		try {
			result = (int) qr.query(sql, new ScalarHandler(), user.getUid());
		} catch (Exception e) {
			System.out.println("getMaxDate错误");
		}
		return result;
	}
	
	public double getAverageScore(User user,Score score){
		double result = -1;
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT SUM(s.score)/COUNT(*) FROM score s WHERE s.uid = ? AND s.flag = ?";
		try {
			result = (double) qr.query(sql, new ScalarHandler(), user.getUid(),score.getFlag());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public double getPassRate(User user){
		double result = 1;
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT COUNT(*)/(SELECT COUNT(*) FROM score s WHERE s.uid = ?) FROM score s WHERE s.uid = ? AND s.score <60;";
		try {
			BigDecimal bigData =  (BigDecimal) qr.query(sql, new ScalarHandler(), user.getUid(),user.getUid());
			if(bigData!=null){
				result = Double.parseDouble(bigData.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
