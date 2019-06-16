package com.muzile.bean;

public class User {
	private int uid;
	private String name;
	private String username;
	private String password;
	private String num;
	private String sex;
	private String role;
	private int fsn;
	private double ascore;
	private double tscore;
	private int count;
	
	
	public double getTscore() {
		return tscore;
	}
	public void setTscore(double tscore) {
		this.tscore = tscore;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAscore() {
		return ascore;
	}
	public void setAscore(double ascore) {
		this.ascore = ascore;
	}
	public int getFsn() {
		return fsn;
	}
	public void setFsn(int fsn) {
		this.fsn = fsn;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", username=" + username + ", password=" + password + ", num="
				+ num + ", sex=" + sex + ", role=" + role + ", fsn=" + fsn + ", ascore=" + ascore + ", tscore=" + tscore
				+ ", count=" + count + "]";
	}
	
	
	
}
