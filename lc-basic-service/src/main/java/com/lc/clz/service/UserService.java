package com.lc.clz.service;

import com.lc.clz.entities.user.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by chenglezheng on 2018/12/28.
 */

public interface UserService {

    User addUser(User user);

    User updateUser(@PathVariable("userId") Long userId);

    User selectUser(@PathVariable("userId") Long userId);

    String deleteUser(@PathVariable("userId") Long userId);

    String deleteAllUser();

    Map<String,Object> selectUserWithPage(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

}
