package com.poi.entity;

import java.sql.Timestamp;

public class CommentsPage {

    private int cmId;

    private String author;

    private int blogId;

    private int fatherId;

    private int total;//楼

    private String comment;

    private Timestamp cm_create;

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCmId() {
        return cmId;
    }

    public void setCmId(int cmId) {
        this.cmId = cmId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getFatherId() {
        return fatherId;
    }

    public void setFatherId(int fatherId) {
        this.fatherId = fatherId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCm_create() {
        return cm_create;
    }

    public void setCm_create(Timestamp cm_create) {
        this.cm_create = cm_create;
    }
}
