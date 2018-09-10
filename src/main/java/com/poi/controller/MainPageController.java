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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/mainPage")
public class MainPageController {
    public static final Logger logger = LoggerFactory.getLogger(MainPageController.class);
    @Autowired
    private ContentService contentService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public String index(){
        logger.info("进入Main页面");
        return "main";
    }

    @ResponseBody
    @RequestMapping(value = "/findContentsInPage/{page}")
    public List<Contents> findContentsInPage(@PathVariable(value = "page") int page){
        logger.info("进入findContentsInPage");
        List<Contents> contentsList = contentService.selectContentsOrderByModified(page-1,5);
        if(contentsList.isEmpty()){
            logger.info("返回为空列表");
            return null;
        }
        logger.info("成功取值");
        return contentsList;
    }

    @ResponseBody
    @RequestMapping(value = "/findContentsById/{id}")
    public Contents findContentsById(@PathVariable(value = "id") int id){
        logger.info("进入findContentsBtId，ID="+id);
        Contents content = contentService.findContentById(id);
        if(null == content){
            logger.info("返回为空");
            content = new Contents();
        }
        logger.info("成功取值");
        return content;
    }

    @RequestMapping(value = "/blog/{id}",method = RequestMethod.GET)
    public String blog(@PathVariable(value = "id") int id, HttpServletRequest request){
        logger.info("进入blog页面");
        setAttributeContent(id,request);
        return "blog";
    }

    @ResponseBody
    @RequestMapping(value = "/setAttributeCon/{id}",method = RequestMethod.GET)
    public void setAttributeContent(@PathVariable(value = "id")int id,HttpServletRequest request){
        logger.info("进入setAttroniteContent页面");
        Contents contents = findContentsById(id);
        request.getSession().setAttribute("content",contents);
    }

    @ResponseBody
    @RequestMapping(value = "/getContent",method = RequestMethod.GET)
    public Contents getContentFromSession(HttpServletRequest request){
        logger.info("getContent");
        Contents content = (Contents) request.getSession().getAttribute("content");
        request.getSession().removeAttribute("content");
        return content;
    }

    @ResponseBody
    @RequestMapping(value = "/getUserById/{id}")
    public User getUserById(@PathVariable(value = "id") int id){
        logger.info("进入getUser,ID="+id);
        User user = userService.getUserById(id);
        if(null == user){
            user = new User();
        }
        return user;
    }
}
