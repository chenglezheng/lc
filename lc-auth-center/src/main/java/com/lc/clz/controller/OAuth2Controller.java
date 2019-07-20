package com.lc.clz.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping
@Api(description = "Oauth2")
public class OAuth2Controller {

    /**
     * 直接返回
     * @param principal
     * @return
     */
    @GetMapping("/user")
    public Principal principal(Principal principal) {
       log.debug("user:",principal.getName());
        return principal;
    }

    @GetMapping("/users/current")
    public String test() {
       return "111";
    }

    @Autowired
    private TokenStore tokenStore;

    /**
     * 退出
     * @param principal
     * @param access_token
     */
    @DeleteMapping(value = "/remove_token", params = "access_token")
    public void removeToken(Principal principal,String access_token) {
        OAuth2AccessToken accessToken=tokenStore.readAccessToken(access_token);
        if (access_token!=null){
            tokenStore.removeAccessToken(accessToken);
            tokenStore.removeRefreshToken(accessToken.getRefreshToken());
        }
        log.info(principal.getName());
    }


}
