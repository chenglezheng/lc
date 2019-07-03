package com.lc.clz.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DomainUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户的权限(我是写死的)
        Set<GrantedAuthority> userAuthotities = new HashSet<>();
        userAuthotities.add(new SimpleGrantedAuthority("query-demo"));
        // 注意如果在WebSecurityConfig.java 中使用的密文时，这个地方也要使用密文
        // return new User("admin", "$2a$10$XOVs4Z1YtPKqKwQVywG9j.xLAqXYRQLGZMGMrZDNbtl6pUC0Weteq", userAuthotities);
        return new User("admin", "admin", userAuthotities);
    }
}
