package com.alai.news.beans;

import java.io.Serializable;

/**
 * 新闻详情实体类
 * Created by Administrator on 2016/1/21 0021.
 */
public class NewsDetailBean implements Serializable {

    /**
     * 编号
     */
    private String docid;

    /**
     * 标题
     */
    private String title;

    /**
     * 来源 如 网易新闻
     */
    private String source;

    /**
     *时间
     */
    private String ptime;

    /**
     * 图片路径
     */
    private String src;

    /**
     * 新闻文字内容
     */
    private String body;

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return ptime;
    }

    public void setTime(String time) {
        this.ptime = time;
    }

    public String getImgUrl() {
        return src;
    }

    public void setImgUrl(String src) {
        this.src = src;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
