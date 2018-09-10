package com.poi.controller;

import com.poi.entity.Contents;
import com.poi.entity.User;
import com.poi.service.ContentService;
import com.poi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserService userService;
    @Autowired
    private ContentService contentService;

    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/findAllUser")
    @ResponseBody
    public List<User> testFindAllUser(){
        logger.info("进入testFindUser");
        return userService.findAll();
    }

    @RequestMapping(value = "/findAllContentsByPage/{page}")
    @ResponseBody
    public List<Contents> findAllContentsByPage(@PathVariable(value = "page") int page){
        return contentService.findAllByPage(page);
    }


    @RequestMapping(value = "/blog")
    public String blog(){
        logger.info("进入blog页面");
        return "blog";
    }

    @RequestMapping(value = "/addContent")
    @ResponseBody
    public void addContent(){
        Contents contents = new Contents();
        contents.setTitle("test");
        contents.setContents("随便");
        contentService.addContents(contents);
    }
}
