package com.lc.clz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import java.util.Scanner;

/**
 * Created by chenglezheng on 2018/12/28.
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ServletComponentScan
@EnableCircuitBreaker
public class Invoker {

    public static void main(String[] args){
        System.out.println("调用服务开始启动");
        System.out.println("请输入端口号，为避免端口号重复，调用服务请以8000端口开始，禁止使用占用端口启动...");
        Scanner scanner=new Scanner(System.in);
        String port=scanner.nextLine();
        new SpringApplicationBuilder(Invoker.class).properties("server.port="+port).run(args);
    }
}
