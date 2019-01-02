package com.lc.clz.controller;

import com.lc.clz.feign.UserFeign;
import com.lc.clz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenglezheng on 2018/12/28.
 */

@RestController
public class UserController {

    @Autowired
    private UserFeign userFeign;

    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public User addUser(User user){
        return userFeign.addUser(user);
    }

    @RequestMapping(value = "/update", produces = {"application/json;charset=UTF-8"})
    public User updateUser(Long userId){
        return userFeign.updateUser(userId);
    }

    @RequestMapping(value = "/select", produces = {"application/json;charset=UTF-8"})
    public User selectUser(Long userId){
        return userFeign.selectUser(userId);
    }

    @RequestMapping(value = "/delete", produces = {"application/json;charset=UTF-8"})
    public String deleteUser(Long userId){
       return userFeign.deleteUser(userId);
    }

    @RequestMapping(value = "/deleteAll", produces = {"application/json;charset=UTF-8"})
    void deleteUser(){

    }

}
