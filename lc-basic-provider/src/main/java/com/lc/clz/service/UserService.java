package com.lc.clz.service;

import com.lc.clz.entities.User;


/**
 * Created by chenglezheng on 2018/12/28.
 */
public interface UserService {
    /**
     * 添加用户
     * @param user
     * @return
     */
    User addUser(User user);


    /**
     * 修改用户
     * @param userId
     * @return
     */
    User updateUser(Long userId);

    /**
     * 查询用户
     * @param userId
     * @return
     */
    User selectUser(String userId);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    void deleteUser(Long userId);

    /**
     * 删除全部用户
     * @param
     * @return
     */
    void deleteUser();

}
