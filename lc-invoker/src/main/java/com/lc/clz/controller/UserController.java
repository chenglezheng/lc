package com.lc.clz.controller;

import com.lc.clz.entities.User;
import com.lc.clz.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenglezheng on 2018/12/28.
 */

@Api(description = "测试接口")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public User addUser(User user){
        return userService.addUser(user);
    }

    @RequestMapping(value = "/update", produces = {"application/json;charset=UTF-8"})
    public User updateUser(Long userId){
        return userService.updateUser(userId);
    }

    @RequestMapping(value = "/select", produces = {"application/json;charset=UTF-8"})
    public User selectUser(Long userId){
        return userService.selectUser(userId);
    }

    @RequestMapping(value = "/delete", produces = {"application/json;charset=UTF-8"})
    public String deleteUser(Long userId){
       return userService.deleteUser(userId);
    }

    @RequestMapping(value = "/deleteAll", produces = {"application/json;charset=UTF-8"})
    void deleteUser(){

    }

}
