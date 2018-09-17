package com.poi.entity;

import org.springframework.data.repository.query.Param;

import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "t_comments")
public class Comments extends BaseEntity {

    @Id
    @Column(name = "cm_id")
    private int cmId;
    @Column(name = "t_author_id")
    private int authorId;
    @Column(name = "blog_id")
    private int blogId;
    @Column(name = "t_father_id")
    private int fatherId;
    @Column(name = "t_total")
    private int total;
    @Column(name = "t_comment")
    private String comment;
    @Column(name = "cm_create")
    private Timestamp cm_create;

    @Override
    public String toString() {
        return "Comments{" +
                "cmId=" + cmId +
                ", authorId=" + authorId +
                ", blogId=" + blogId +
                ", fatherId=" + fatherId +
                ", total=" + total +
                ", comment='" + comment + '\'' +
                ", cm_create=" + cm_create +
                '}';
    }

    public int getFatherId() {
        return fatherId;
    }

    public void setFatherId(int fatherId) {
        this.fatherId = fatherId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

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
