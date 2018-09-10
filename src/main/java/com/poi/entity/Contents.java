package com.poi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "t_contents")
public class Contents extends BaseEntity{
    @Id
    private int cid;
    @Column(name = "title")
    private String title;
    @Column(name = "t_contents")
    private String contents;
    @Column(name = "t_create")
    private Timestamp create;
    @Column(name = "t_modified")
    private Timestamp modified;//更新时间
    @Column(name = "t_type")
    private int type;
    @Column(name = "t_status")
    private int status;
    @Column(name = "t_author_id")
    private int authorId;
    @Column(name = "t_hits")
    private int hits;
    @Column(name = "t_allow_comment")
    private int allowComment;
    @Column(name = "t_comments_num")
    private int commentsNum;
    @Column(name = "t_url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCId() {
        return cid;
    }

    public void setCId(int cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Timestamp getCreate() {
        return create;
    }

    public void setCreate(Timestamp create) {
        this.create = create;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Contents{" +
                "cId=" + cid +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", create=" + create +
                ", modified=" + modified +
                ", type=" + type +
                ", status=" + status +
                ", authorId=" + authorId +
                ", hits=" + hits +
                ", allowComment=" + allowComment +
                ", commentsNum=" + commentsNum +
                '}';
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(int allowComment) {
        this.allowComment = allowComment;
    }

    public int getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(int commentsNum) {
        this.commentsNum = commentsNum;
    }
}
