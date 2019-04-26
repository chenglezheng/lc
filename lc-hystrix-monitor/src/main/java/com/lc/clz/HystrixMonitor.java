package com.lc.clz;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

import java.util.Scanner;

@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine
public class HystrixMonitor {

    public static void main(String[] args) {
        new SpringApplicationBuilder(HystrixMonitor.class).run(args);
    }
}
