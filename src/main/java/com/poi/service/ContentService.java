package com.poi.service;

import com.poi.entity.Contents;
import com.poi.jpa.ContentJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ContentService {
    @Autowired
    private ContentJpa contentJpa;

    public List<Contents> findAll(){
        return contentJpa.findAll();
    }

    public List<Contents> findAllByUserId(int authorId){
        return contentJpa.findAllByUserId(authorId);
    }

    public List<Contents> findAllByUserIdInPage(int authorId,int page,int pageSize){
        Contents contents = new Contents();
        contents.setPage(page);
        contents.setPageSize(pageSize);
        contents.setSidx("modified");
        contents.setSord("desc");

        return contentJpa.findAllByAuthorIdOnPage(authorId,contents.getSidx(),contents.getSord(),page,pageSize);
    }

    public List<Contents> findAllByPage(int page){
        Contents contents = new Contents();
        contents.setPage(page);
        PageRequest pageRequest = new PageRequest(contents.getPage(),contents.getPageSize());
        return contentJpa.findAll(pageRequest).getContent();
    }

    public void deleteBlog(int blogId){
        contentJpa.deleteById(blogId);
    }

    public List<Contents> selectContentsOrderByModified(int page,int pageSize){
        Contents contents = new Contents();
        contents.setPage(page);
        contents.setPageSize(pageSize);
        contents.setSidx("modified");
        contents.setSord("desc");

        Sort.Direction sortDirection = Sort.Direction.ASC.toString().equalsIgnoreCase(contents.getSord())?Sort.Direction.ASC:Sort.Direction.DESC;
        Sort sort = new Sort(sortDirection,contents.getSidx());
        PageRequest pageRequest = new PageRequest(contents.getPage(),contents.getPageSize(),sort);
        return contentJpa.findAll(pageRequest).getContent();
    }

    public Contents findContentById(int id){
        Optional<Contents> contents = contentJpa.findById(new Integer(id));
        Contents result = null;
        if(!Optional.empty().equals(contents)){
            result = contents.get();
        }
        return result;
    }

    public void updateContents(Contents contents){
        Contents con = contentJpa.getOne(contents.getCId());
        try{
            String titleStr = (contents.getTitle()==null)?" ":contents.getTitle();
            con.setTitle(titleStr);
            String blogStr = (contents.getContents()==null)?" ":contents.getContents();
            con.setContents(blogStr);
            Timestamp timeNow = new Timestamp(System.currentTimeMillis());
            con.setModified(timeNow);
            contentJpa.save(con);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Contents addContents(Contents contents){
        Contents contents1 = new Contents();
        try {
//            contentJpa.save(contents1);
            if (null != contents.getTitle()) {
                contents1.setTitle(contents.getTitle());
            }
            if (null != contents.getContents()) {
                contents1.setContents(contents.getContents());
            }
            if(null != contents.getUrl()){
                contents1.setUrl(contents.getUrl());
            }
            contents1.setAuthorId(contents.getAuthorId());
            contents1.setAllowComment(contents.getAllowComment());
            contents1.setType(contents.getType());
            contents1.setStatus(contents.getStatus());
            Timestamp timeNow = new Timestamp(System.currentTimeMillis());
            contents1.setCreate(timeNow);
            contents1.setModified(timeNow);
        }catch (Exception e){
            e.printStackTrace();
        }
        return contentJpa.save(contents1);
    }
}
