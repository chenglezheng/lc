package com.lc.clz.shiro;

import com.lc.clz.dao.UserDao;
import com.lc.clz.entity.User;
import com.lc.clz.mapper.UsersMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by chenglezheng on 2018/12/20.
 */
public class ShiroRealm extends AuthorizingRealm{

    @Resource
    private UsersMapper usersMapper;

    /**
     * 重写授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName=(String) principalCollection.getPrimaryPrincipal();
        //1.从数据库或者缓存中获取角色数据
        Set<String> roles=getRoleByUserName(userName);
        //2.从数据库或者缓存中获取权限数据
        Set<String> permissions=getPermissionByUserName(userName);
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(permissions);
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    /**
     * 重写认证登录方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.从主体传过来的认证信息中，获得用户名
        String userName=(String) authenticationToken.getPrincipal();
        //2.通过用户名到数据库中获取凭证
        String password=getPasswordByUserName(userName);
        if (password==null){
            return  null;
        }
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo("clz",password,"customerRealm");
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("*#~"));
        return authenticationInfo;
    }

    /**
     * 从数据库中获取用户权限
     * @param userName
     * @return
     */
    private Set<String> getPermissionByUserName(String userName){
        List<String> list=userDao.getPermissionByUserName(userName);
        Set<String> set=new HashSet<String>(list);
        return set;
    }


    /**
     * 从数据库中获取用户角色
     * @param userName
     * @return
     */
    private Set<String> getRoleByUserName(String userName){
        List<String> list=userDao.getRoleByUserName(userName);
        Set<String> set=new HashSet<String>(list);
        return set;
    }

    /**
     * 数据库中获取密码
     * @param userName
     * @return
     */
    private String getPasswordByUserName(String userName){
        User user=userDao.getUserByUserName(userName);
        if (user!=null){
            return user.getPassword();
        }
        return null;
    }

}
