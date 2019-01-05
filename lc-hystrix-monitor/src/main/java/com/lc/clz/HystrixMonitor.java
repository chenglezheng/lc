package com.lc.clz;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import java.util.Scanner;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixMonitor {

    public static void main(String[] args) {
        new SpringApplicationBuilder(HystrixMonitor.class).run(args);
    }
}
