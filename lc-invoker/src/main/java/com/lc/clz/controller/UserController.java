package com.lc.clz.controller;

import com.lc.clz.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenglezheng on 2018/12/28.
 */

@RestController
/*@Configuration*/
public class UserController {

    @Autowired
    private UserFeign userFeign;

    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        return userFeign.findAllUser(pageNum,pageSize);
    }

}
