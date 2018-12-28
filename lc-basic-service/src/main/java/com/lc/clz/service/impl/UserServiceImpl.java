package com.lc.clz.service.impl;

import com.github.pagehelper.PageHelper;
import com.lc.clz.mapper.UserMapper;
import com.lc.clz.model.User;
import com.lc.clz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenglezheng on 2018/12/28.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    public List<User> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAllUser();
    }
}
