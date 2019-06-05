package com.lc.clz.controller;

import com.lc.clz.entities.User;
import com.lc.clz.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by chenglezheng on 2018/12/28.
 */

@Api(description = "测试接口")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public User addUser(User user){
        return userService.addUser(user);
    }

    @GetMapping(value = "/update", produces = {"application/json;charset=UTF-8"})
    public User updateUser(Long userId){
        return userService.updateUser(userId);
    }

    @GetMapping(value = "/select", produces = {"application/json;charset=UTF-8"})
    public User selectUser(Long userId){
        return userService.selectUser(userId);
    }

    @GetMapping(value = "/delete", produces = {"application/json;charset=UTF-8"})
    public String deleteUser(Long userId){
       return userService.deleteUser(userId);
    }

    @GetMapping(value = "/deleteAll", produces = {"application/json;charset=UTF-8"})
    void deleteUser(){}

    /**
     * 分页查询用户
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/selectUserWithPage", produces = {"application/json;charset=UTF-8"})
    public Map<String,Object> selectUserWithPage(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return userService.selectUserWithPage(page,limit);
    }

}
