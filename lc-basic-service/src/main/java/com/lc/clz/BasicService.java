package com.lc.clz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Scanner;

/**
 * Created by chenglezheng on 2018/12/28.
 */
@MapperScan("com.lc.clz.mapper")
@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement /*开启事务管理*/
public class BasicService {

    public static void main(String[] args){
        System.out.println("提供者服务开始启动");
        System.out.println("请输入端口号，为避免端口号重复，提供者服务请以9000端口开始，禁止使用占用端口启动...");
        Scanner scanner=new Scanner(System.in);
        String port=scanner.nextLine();
        new SpringApplicationBuilder(BasicService.class).properties("server.port="+port).run(args);
    }
}
