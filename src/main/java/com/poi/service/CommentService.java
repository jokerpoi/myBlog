package com.poi.service;

import com.poi.entity.*;
import com.poi.jpa.CommentJpa;
import com.poi.jpa.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentJpa commentJpa;
    @Autowired
    private UserService userService;

    public List<Comments> findAll(){
        return commentJpa.findAll();
    }

    public Comments addNewComments(Comments comments){
        Comments result = checkComments(comments);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        result.setCm_create(now);
        return commentJpa.save(result);
    }


    public Comments checkComments(Comments comments){
        Comments result = new Comments();
        if(comments.getCmId() != 0){
            result.setCmId(comments.getCmId());
        }
        if(comments.getAuthorId() != 0){
            result.setAuthorId(comments.getAuthorId());
        }
        if(comments.getBlogId() != 0){
            result.setBlogId(comments.getBlogId());
        }
        if(comments.getFatherId() != 0){
            result.setFatherId(comments.getFatherId());
        }
        if(comments.getComment() != null && !comments.getComment().isEmpty()){
            result.setComment(comments.getComment());
        }
        result.setTotal(comments.getTotal());
        return result;
    }

    public CommentsPage changeComments(Comments comments){
        CommentsPage result = new CommentsPage();
        if(comments.getCmId() != 0){
            result.setCmId(comments.getCmId());
        }
        if(comments.getAuthorId() != 0){
            User user = userService.getUserById(comments.getAuthorId());
            result.setAuthor(user.getUsername());
        }
        if(comments.getBlogId() != 0){
            result.setBlogId(comments.getBlogId());
        }
        if(comments.getFatherId() != 0){
            result.setFatherId(comments.getFatherId());
        }
        if(comments.getComment() != null && !comments.getComment().isEmpty()){
            result.setComment(comments.getComment());
        }
        result.setTotal(comments.getTotal());
        result.setCm_create(comments.getCm_create());
        return result;
    }

    public List<CommentsPage> findListCommentsByBlogId(int blogId){
        List<CommentsPage> result = new ArrayList<>();
        List<Comments> userAndCommentsList = commentJpa.findListByBlogId(blogId);
        for(Comments node : userAndCommentsList){
            CommentsPage page = changeComments(node);
            result.add(page);
        }
        return result;
    }

    public List<CommentsPage> findListCommentsByFatherId(int fatherId){
        List<CommentsPage> result = new ArrayList<>();
        List<Comments> userAndCommentsList = commentJpa.findListByFatherIdId(fatherId);
        for(Comments node : userAndCommentsList){
            CommentsPage page = changeComments(node);
            result.add(page);
        }
        return result;
    }

    public DataGrid<CommentsPage> findPageByFatherId(Comments comments,int limit,int offset){
        DataGrid<CommentsPage> dataGrid = new DataGrid<>();

        BaseEntity baseEntity = new BaseEntity();
        baseEntity.setPage(offset);
        baseEntity.setPageSize(limit);
        baseEntity.setSidx("t_create");
        baseEntity.setSord("asc");

        List<CommentsPage> commentsList = findListCommentsByFatherId(comments.getFatherId());
        List<Comments> commentsListOnPage = commentJpa.findListByFatherIdOnPage(comments.getFatherId(),
                baseEntity.getSidx(),baseEntity.getSord(),offset,limit);
        List<CommentsPage> resultRows = new ArrayList<>();
        for(Comments node : commentsListOnPage){
            CommentsPage commentsPage = changeComments(node);
            resultRows.add(commentsPage);
        }

        dataGrid.setTotal(commentsList.size());
        dataGrid.setRows(resultRows);

        return dataGrid;
    }

}
