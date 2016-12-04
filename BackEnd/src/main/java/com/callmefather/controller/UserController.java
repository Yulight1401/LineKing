package com.callmefather.controller;

import com.callmefather.dao.UserDAO;
import com.callmefather.enity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 * Created by xinliu on 12/2/16.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value = "/logon", method = RequestMethod.POST)
    public void newUser(HttpServletRequest request, HttpServletResponse response) {
        String name = ControllerUtil.getParam(request, "name", null);
        String password = ControllerUtil.getParam(request, "password", null);

        if (name == null || password == null) {
            ControllerUtil.setState(false, response);
        } else {
            User user = new User(name, password);
            System.out.println(new Timestamp(System.currentTimeMillis()) + "--" + request.getLocalAddr() + "--new User--" + user.getName() + "--" + user.getPassword());
            boolean success = UserDAO.newUser(user);
            System.out.println(success);
            ControllerUtil.setState(success, response);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response) {
        String name = ControllerUtil.getParam(request, "name", null);
        String password = ControllerUtil.getParam(request, "password", null);
        if (name == null || password == null) {
            ControllerUtil.setState(false, response);
        } else {
            User user = new User(name, password);
            boolean success = UserDAO.isLoginSuccess(user);
            System.out.println(success ? user : "failed");
            ControllerUtil.setState(success, response);
        }
    }


}


