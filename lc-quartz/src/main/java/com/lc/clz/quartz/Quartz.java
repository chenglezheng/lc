package com.lc.clz.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Quartz {

    public static void main(String[] args) {
        SpringApplication.run(Quartz.class, args);
    }

}
