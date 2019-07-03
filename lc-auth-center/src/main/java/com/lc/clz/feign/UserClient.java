package com.lc.clz.feign;

import com.lc.clz.entities.user.LoginAppUser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="lc-user-center",fallback = UserClient.UserClientCallback.class)
public interface UserClient {

    @GetMapping(value = "/users-anon/internal", params = "username")
    LoginAppUser findByUsername(@RequestParam("username") String username);

    @Component
    class UserClientCallback implements  UserClient{
        @Override
        public LoginAppUser findByUsername(String username) {
            LoginAppUser loginAppUser=new LoginAppUser();
            loginAppUser.setUsername("服务已暂停!");
            return loginAppUser;
        }
    }

}
