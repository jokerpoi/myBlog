package com.poi.service;

import com.poi.entity.Contents;
import com.poi.entity.DataGrid;
import com.poi.entity.User;
import com.poi.entity.UserUtilPageTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserUtilPageTableService {

    private static final Logger logger = LoggerFactory.getLogger(UserUtilPageTableService.class);
    @Autowired
    private ContentService contentService;
    @Autowired
    private UserService userService;

    public DataGrid findListCommentPage(int limit,int offset){
        logger.info("进入findListCommentPage");
        DataGrid<UserUtilPageTable> dataGrid = new DataGrid();
        List<Contents> contentsAll = contentService.findAll();
        List<Contents> contents = contentService.selectContentsOrderByModified(offset,limit);
        List<UserUtilPageTable> pageTableList = new ArrayList<>();
        for (Contents cont:contents) {
            UserUtilPageTable page = changeContentToUserUtilPage(cont);
            pageTableList.add(page);
        }
        dataGrid.setTotal(contentsAll.size());
        dataGrid.setRows(pageTableList);
        return dataGrid;
    }

    public UserUtilPageTable changeContentToUserUtilPage(Contents contents){
        UserUtilPageTable userUtilPageTable = new UserUtilPageTable();
        userUtilPageTable.setCId(contents.getCId());
        if(null != contents.getTitle() && !contents.getTitle().isEmpty()){
            userUtilPageTable.setTitle(contents.getTitle());
        }
        if(contents.getAuthorId() != 0){
            User user = userService.getUserById(contents.getAuthorId());
            if(null == user){
                userUtilPageTable.setAuthor("");
            }else{
                userUtilPageTable.setAuthor(user.getUsername());
            }
        }
        if(null != contents.getUrl() && !contents.getUrl().isEmpty()){
            userUtilPageTable.setUrl(contents.getUrl());
        }
        switch (contents.getStatus()){
            case 0:userUtilPageTable.setStatus("未审核");break;
            case 1:userUtilPageTable.setStatus("审核中");break;
            case 2:userUtilPageTable.setStatus("审核通过");break;
        }
        switch (contents.getAllowComment()){
            case 0:userUtilPageTable.setAllowComment(false);break;
            case 1:userUtilPageTable.setAllowComment(true);break;
        }
        userUtilPageTable.setHits(contents.getHits());
        switch (contents.getType()){

        }
        Timestamp create = contents.getCreate();
        Timestamp modified = contents.getModified();
        userUtilPageTable.setCreateTime(changeDateFormate(create));
        userUtilPageTable.setModifiedTime(changeDateFormate(modified));
        userUtilPageTable.setCommentNum(contents.getCommentsNum());
        return userUtilPageTable;
    }

    public String changeDateFormate(Timestamp time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = time.getTime();
        Date date = new Date(lt);
        String result = simpleDateFormat.format(date);
        return result;
    }
}
