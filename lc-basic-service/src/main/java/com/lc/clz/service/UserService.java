package com.lc.clz.service;

import com.lc.clz.model.User;

import java.util.List;

/**
 * Created by chenglezheng on 2018/12/28.
 */
public interface UserService {
    /**
     * 添加用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 分页查询用户信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<User> findAllUser(int pageNum, int pageSize);
}
