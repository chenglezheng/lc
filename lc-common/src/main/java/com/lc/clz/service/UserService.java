package com.lc.clz.service;

import com.lc.clz.entities.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenglezheng on 2018/12/28.
 */

@FeignClient(name = "lc-basic-service",fallback = UserService.UserServiceCallback.class)
public interface UserService {

    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    User addUser(User user);

    @RequestMapping(value = "/update/{userId}", produces = {"application/json;charset=UTF-8"})
    User updateUser(@PathVariable("userId") Long userId);

    @RequestMapping(value = "/select/{userId}", produces = {"application/json;charset=UTF-8"})
    User selectUser(@PathVariable("userId") Long userId);

    @RequestMapping(value = "/delete/{userId}", produces = {"application/json;charset=UTF-8"})
    String deleteUser(@PathVariable("userId") Long userId);

    @RequestMapping(value = "/deleteAll", produces = {"application/json;charset=UTF-8"})
    String deleteAllUser();

    @Component
    class UserServiceCallback implements UserService {
        public User addUser(User user) {
            user=new User();
            user.setUserName("暂停服务");
            return user;
        }

        public User updateUser(Long userId) {
            return null;
        }

        public User selectUser(Long userId) {
            User user =new User();
            user.setUserName("暂停服务");
            return user;
        }

        public String deleteUser(Long userId) {
            return "";
        }

        public String deleteAllUser() {
            return "";
        }
    }
}
