package com.gs.controller;

import com.gs.bean.User;
import com.gs.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

public class UserController extends ActionSupport {

    /**
     * 给需要输出日志的类获取相应的日志记录器，参数是类的class
     */
    private static final Logger logger = Logger.getLogger(UserController.class);

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String add() {
        User user = new User();
        user.setPhone("test1111");
        userService.save(user);
        // 调用方法记录相关日志
        logger.trace("trace");
        logger.debug("添加用户");
        logger.info("info级别");
        logger.warn("warn");
        logger.error("error");
        return "add";
    }

    public String remove() {
        User user = new User();
        user.setId("402880345dc9b50f015dc9b5c6fe0001");
        userService.remove(user);
        return "remove";
    }

}
