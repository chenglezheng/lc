package com.lc.clz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by chenglezheng on 2018/12/28.
 */
@SpringBootApplication
@EnableEurekaClient  //服务客户端，让该服务注册到Eureka中
@EnableDiscoveryClient //服务发现
@EnableTransactionManagement /*开启事务管理*/
@MapperScan("com.lc.clz.dao")  //扫描mapper，否则启动会报找不到mapper
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserCenter {


    public static void main(String[] args){
        new SpringApplicationBuilder(UserCenter.class).run(args);
    }
}
