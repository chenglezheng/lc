package com.lc.clz.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lc.clz.dao.UserDao;
import com.lc.clz.entities.user.User;
import com.lc.clz.service.UserService;
import com.lc.clz.utils.RedisUtils;
import com.lc.clz.utils.TableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by chenglezheng on 2018/12/28.
 */
@Service("userServiceImpl")
@CacheConfig(cacheNames="userCache") // 本类内方法指定使用缓存时，默认的名称就是userCache
@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=Throwable.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userMapper;

    @Autowired
    private RedisUtils redisUtil;

    private  final  String  PREFIX ="Basic_Provider_UserServiceImpl_";

    @Override
    public Map<String,Object> selectUserWithPage(Integer page, Integer limit) {
        PageHelper.startPage(page-1,limit);
        List<User> users=userMapper.selectAll();
        PageInfo<User> pageInfo=new PageInfo<>(users);
        return TableUtils.getTable(pageInfo.getTotal(),users);
    }


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
        User user=this.selectUser(userId);
        user.setUserName("clz");
        user.setUserPwd("123");
        userMapper.updateByPrimaryKey(user);
        return this.selectUser(user.getUserId());
    }

    @Cacheable(key="'com.lc.clz.entities.User'+#userId") // @Cacheable 会先查询缓存，如果缓存中存在，则不执行方法
    public User selectUser(Long userId) {
        User user = (User) redisUtil.get(PREFIX+userId.toString());
        if (user!=null){
          return user;
        }
        System.out.println("从数据库中获取用户信息");
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    @CacheEvict(key="#p0")  //删除缓存名称为userCache,key等于指定的id对应的缓存
    public String deleteUser(Long userId) {
        return null;
    }

    @Override
    public String deleteAllUser() {
        return null;
    }

    /*@Override
    public Integer backLoginFlag(String userName, String userPassword) {
        return null;
    }*/
}
