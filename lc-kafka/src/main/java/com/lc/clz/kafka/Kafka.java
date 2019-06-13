package com.lc.clz.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Kafka {

    public static void main(String[] args) {
        SpringApplication.run(Kafka.class, args);
    }

}
