package com.alai.news.beans;

import java.io.Serializable;

/**
 * 新闻列表实体类
 * Created by Administrator on 2016/1/21 0021.
 */
public class NewsBean implements Serializable {

    private int no;

    private String postid;
    private String docid;
    private String imgsrc; //列表第一张照片
    private String title;
    private String digest;
    private String source;
    private String ptime;

    private String skipType;  //浏览方式  普通的为空     图集为photoset  视频为video

    private String imgsrc2;
    private String imgsrc3;
    private String videosrc;


    private String body;


    public String getPtime() {
        return ptime;
    }
    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getDocid() {
        return docid;
    }
    public void setDocid(String docid) {
        this.docid = docid;
    }
    public String getImgsrc() {
        return imgsrc;
    }
    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }
    public String getImgsrc2() {
        return imgsrc2;
    }
    public void setImgsrc2(String imgsrc2) {
        this.imgsrc2 = imgsrc2;
    }
    public String getImgsrc3() {
        return imgsrc3;
    }
    public void setImgsrc3(String imgsrc3) {
        this.imgsrc3 = imgsrc3;
    }
    public String getVideosrc() {
        return videosrc;
    }
    public void setVideosrc(String videosrc) {
        this.videosrc = videosrc;
    }
    public String getSkipType() {
        return skipType;
    }
    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDigest() {
        return digest;
    }
    public void setDigest(String digest) {
        this.digest = digest;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getPostid() {
        return postid;
    }
    public void setPostid(String postid) {
        this.postid = postid;
    }
}
