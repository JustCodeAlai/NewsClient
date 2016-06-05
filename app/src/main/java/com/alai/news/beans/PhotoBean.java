package com.alai.news.beans;

import java.io.Serializable;

public class PhotoBean implements Serializable {
	
	/**
	 * 
	 */
	private int no;
	private String imgsrc;
	private String title;
	private int width;
	private int height;
	private NewsBean news;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public NewsBean getNews() {
		return news;
	}
	public void setNews(NewsBean news) {
		this.news = news;
	}
	
	/*public String getPostid() {
		return postid;
	}
	public void setPostid(String postid) {
		this.postid = postid;
	}
	public String getPhotoid() {
		return photoid;
	}
	public void setPhotoid(String photoid) {
		this.photoid = photoid;
	}*/
}
