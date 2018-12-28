package com.lc.clz;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.Scanner;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {
    public static void main(String[] args) {
        //读取控制台输入，决定使用哪个profiles
        System.out.println("请输入服务名称，服务名称为“server1”或“server2”，请尽量同时启动这两个服务，以免控制台出现报错信息...");
        Scanner scanner = new Scanner(System.in);
        String profiles = scanner.nextLine();
        new SpringApplicationBuilder(EurekaServer.class).profiles(profiles).run(args);
    }
}
