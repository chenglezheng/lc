package com.lc.clz;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import java.util.Scanner;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixMonitor {

    public static void main(String[] args) {
        System.out.println("熔断监视服务开始启动");
        System.out.println("请输入端口号，为避免端口号重复，调用服务请以1003端口开始，禁止使用占用端口启动...");
        Scanner scanner=new Scanner(System.in);
        String port=scanner.nextLine();
        new SpringApplicationBuilder(HystrixMonitor.class).properties("server.port="+port).run(args);
    }
}
