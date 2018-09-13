package com.poi.service;

import com.poi.controller.SessionConfiguration;
import com.poi.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(UserInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("拦截的url" + request.getRequestURI()) ;
        if(request.getRequestURI().equals("/userPage/login") || request.getRequestURI().equals("/userPage/loginPage")){
            return true;
        }
        if(request.getRequestURI().equals("/userPage/registerPage") || request.getRequestURI().equals("/userPage/addUser")){
            return true;
        }
        //验证是否存在session
        Object user = request.getSession().getAttribute("user");

        if(null == user){
            response.sendRedirect("/userPage/loginPage");
            return false;
        }
        return true;
    }
}
