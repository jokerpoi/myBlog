package com.poi.controller;

import com.poi.entity.User;
import com.poi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
        userService.addUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/updateUser")
    public void updateUser(User user,HttpServletRequest request){
        logger.info("进入updateUser");
        logger.info("User:"+user.toString());
        User locationUser = getSessionUser(request);
        if(locationUser.getUid() != 0){
            user.setUid(locationUser.getUid());
            userService.updateUser(user);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/getSessionUser")
    public User getSessionUser(HttpServletRequest request){
        Object obj = request.getSession().getAttribute("user");
        if(null != obj){
            return (User)obj;
        }else{
            return new User();
        }
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

    @ResponseBody
    @RequestMapping(value = "/findUserByName/{name}")
    public User findUserByName(@PathVariable("name") String username){
        logger.info("findUserByName,Name= " +username);
        return userService.findUserByName(username);
    }

    @ResponseBody
    @RequestMapping(value = "/login")
    public String login(User u,HttpServletRequest request){
        logger.info("login:  name= "+ u.toString());
        User user = findUserByName(u.getUsername());
        if(null == user || user.getUid()==0){
            return "nameNull";
        }
        logger.info("user = "+user.toString());
        if(!user.getPassword().equals(u.getPassword())){
            return "passwordError";
        }
        request.getSession().setAttribute("user",user);
        return "登录成功!";
    }
}
