package com.lc.clz;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableZuulProxy
@SpringBootApplication
@EnableFeignClients
public class Zuul{

    public static void main(String[] args) {
        new SpringApplicationBuilder(Zuul.class).run(args);
    }


}
