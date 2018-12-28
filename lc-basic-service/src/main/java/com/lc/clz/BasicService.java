package com.lc.clz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by chenglezheng on 2018/12/28.
 */
@MapperScan("com.lc.clz.mapper")
@SpringBootApplication
@EnableEurekaClient
public class BasicService {

    public static void main(String[] args){
        new SpringApplicationBuilder(BasicService.class).run(args);
    }
}
