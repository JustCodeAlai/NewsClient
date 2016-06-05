package com.alai.news.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserBean implements Serializable {

	/**
	 * 
	 */
	
	private int no;
	private String name;
	private String password;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
