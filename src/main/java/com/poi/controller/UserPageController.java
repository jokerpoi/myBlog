package com.poi.controller;

import com.poi.entity.User;
import com.poi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/userPage")
public class UserPageController {
    private static final Logger logger = LoggerFactory.getLogger(UserPageController.class);

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/addUser")
    public void addUser(User user){
        logger.info("进入AddUser");
        logger.info("User:"+user.toString());
//        userService.addUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/updateUser")
    public void updateUser(User user){
        logger.info("进入updateUser");
        logger.info("User:"+user.toString());
//        userService.updateUser(user);
    }

    @RequestMapping(value = "/registerPage")
    public String registerPage(){
        logger.info("registerPage");
        return "registerPage";
    }

    @RequestMapping(value = "/loginPage")
    public String loginPage(){
        logger.info("loginPage");
        return "loginPage";
    }
}
