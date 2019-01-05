package com.lc.clz.service.impl;

import com.lc.clz.mapper.UserMapper;
import com.lc.clz.entities.User;
import com.lc.clz.service.UserService;
import com.lc.clz.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chenglezheng on 2018/12/28.
 */
@Service
@CacheConfig(cacheNames="userCache") // 本类内方法指定使用缓存时，默认的名称就是userCache
@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=Throwable.class)
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    private  final  String  PREFIX ="Basic_Provider_UserServiceImpl_";


    // 因为必须要有返回值，才能保存到数据库中，如果保存的对象的某些字段是需要数据库生成的，那保存对象进数据库的时候，就没必要放到缓存了
    @CachePut(key="#p0.userName.toString()")  //#p0表示第一个参数 (必须要有返回值，否则没数据放到缓存中)
    public User addUser(User user) {
        user=new User();
        user.setUserName("clz");
        user.setUserPwd("clz1234567");
        user.setUserRole(1);
        userMapper.insertSelective(user);
       /* redisUtil.set(PREFIX+user.getUserName(),user);
        redisUtil.expire(PREFIX+user.getUserName(),1000);*/
        return user;
    }

    @CachePut(key="#p0.id")
    public User updateUser(Long userId) {
        User user=this.selectUser(userId.toString());
        user.setUserName("clz");
        user.setUserPwd("123");
        userMapper.updateByPrimaryKey(user);
        return this.selectUser(user.getUserId().toString());
    }

    @Cacheable(key="#p0") // @Cacheable 会先查询缓存，如果缓存中存在，则不执行方法
    public User selectUser(String userId) {
        User user = (User) redisUtil.get(PREFIX+userId.toString());
        if (user!=null){
          return user;
        }
        System.out.println("从数据库中获取用户信息");
        return userMapper.selectByPrimaryKey(Long.parseLong(userId));
    }

    @CacheEvict(key="#p0")  //删除缓存名称为userCache,key等于指定的id对应的缓存
    public void deleteUser(Long userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    public void deleteUser() {

    }

}
