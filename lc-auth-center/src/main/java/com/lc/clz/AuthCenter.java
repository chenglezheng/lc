package com.lc.clz;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 认证中心
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient  //服务客户端，让该服务注册到Eureka中
@EnableDiscoveryClient //服务发现
@EnableConfigurationProperties(SecurityProperties.class)
public class AuthCenter {

    public static void main(String[] args){
        new SpringApplicationBuilder(AuthCenter.class).run(args);
    }
}
