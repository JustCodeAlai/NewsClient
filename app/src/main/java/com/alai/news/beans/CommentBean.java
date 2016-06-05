package com.alai.news.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class CommentBean implements Serializable {

	/**
	 * 
	 */
	
    private int no;
    private Timestamp time;
    private String content;
    
    private UserBean user;
    private NewsBean news;
    
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	/*public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}*/
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	public NewsBean getNews() {
		return news;
	}
	public void setNews(NewsBean news) {
		this.news = news;
	}
    
    
}
