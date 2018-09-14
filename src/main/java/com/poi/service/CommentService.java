package com.poi.service;

import com.poi.entity.Comments;
import com.poi.jpa.CommentJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentJpa commentJpa;

    public List<Comments> findAll(){
        return commentJpa.findAll();
    }
}
