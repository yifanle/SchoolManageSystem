package com.muzile.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.muzile.bean.Result;
import com.muzile.bean.Score;
import com.muzile.bean.Subject;
import com.muzile.bean.User;
import com.muzile.utils.DataSourceUtils;

public class TeacherDao {
	public List<Result> getStuScore(User user,Score score){
		List<Result> list = null;
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM score s LEFT OUTER JOIN SUBJECT sb ON s.suid = sb.suid WHERE s.uid = ? and s.date = ? ORDER BY sid asc";
		try {
			list = qr.query(sql, new BeanListHandler<Result>(Result.class), user.getUid(),score.getDate());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void update(Result res) {
		try {
			QueryRunner qr = new QueryRunner();
			Connection conn = DataSourceUtils.getCurrentConnection();
			String sql = "update score set score = ? where sid =?";
			qr.update(conn, sql, res.getScore(),res.getSid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		try {
			QueryRunner qr = new QueryRunner();
			Connection conn = DataSourceUtils.getCurrentConnection();
			String sql = "delete from score where sid = ?";
			qr.update(conn, sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public List<Subject> getSubjects(){
		List<Subject> list = null;
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from subject";
		try {
			list = qr.query(sql, new BeanListHandler<Subject>(Subject.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void add(Score score) {
		try {
			QueryRunner qr = new QueryRunner();
			Connection conn = DataSourceUtils.getCurrentConnection();
			String sql = "INSERT INTO score VALUES (NULL,?,?,?,?,?);";
			qr.update(conn, sql, score.getSuid(),score.getScore(),score.getDate(),score.getUid(),score.getFlag());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isExist(Score score){
		Score result = null;
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from score where suid = ? and uid = ? and date = ?";
		try {
			result = qr.query(sql, new BeanHandler<Score>(Score.class), score.getSuid(),score.getUid(),score.getDate());
			if(result!=null){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<User> getAnalysis4Rank(Score score){
		List<User> list = new ArrayList<User>();
		List<User> t_res = null;
		List<User> c_res = null;
 		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String t_sql = "SELECT(SELECT NAME FROM USER u WHERE u.uid = s.`uid` ) name, SUM(score) tscore FROM score s WHERE date = ? GROUP BY uid";
		String c_sql = "SELECT (SELECT NAME FROM USER u WHERE u.uid = s.`uid` ) name, COUNT(*) count FROM score s WHERE date = ? GROUP BY uid";
		Object parm = score.getDate();
		try {
			t_res = qr.query(t_sql, new BeanListHandler<User>(User.class),parm);
			c_res = qr.query(c_sql, new BeanListHandler<User>(User.class),parm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(User t_user : t_res){
			for(User c_user : c_res){
				if(t_user.getName().equals(c_user.getName())){
					User user = new User();
					user.setName(t_user.getName());
					user.setAscore(t_user.getTscore()/c_user.getCount());
					list.add(user);
				}
			}
		}
		return list;
	}
	
	public double getPassRate(Score score){
		String f_sql = "SELECT COUNT(*) FROM score s WHERE date = ? AND s.`score`<60 GROUP BY uid";
		String t_sql = "SELECT COUNT(*) FROM USER WHERE role = '学生'";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		double passrate = 0;
		try {
			Long fobj =  (Long) qr.query(f_sql, new ScalarHandler(), score.getDate());
			Long tobj =  (Long) qr.query(t_sql, new ScalarHandler());
			
			if(fobj==null){
				return passrate;
			}
			if(tobj==null){
				return 1-passrate;
			}
			passrate = Double.parseDouble(fobj+"")/Double.parseDouble(tobj+"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(score.getDate());
		return passrate;
	}
}
