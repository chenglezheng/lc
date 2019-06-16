package com.lc.clz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置。
 * @author Ben
 * @since 1.0.0
 * @version 1.0.0
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	@Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
}
