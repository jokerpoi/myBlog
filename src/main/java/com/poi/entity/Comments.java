package com.poi.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Timestamp;

@Table(name = "")
public class Comments {

    @Id
    @Column(name = "")
    private int cmId;
    @Column(name = "")
    private int authorId;
    @Column(name = "")
    private int fatherId;
    @Column(name = "")
    private String comment;
    @Column(name = "")
    private Timestamp cm_create;

    public int getCmId() {
        return cmId;
    }

    public void setCmId(int cmId) {
        this.cmId = cmId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
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
