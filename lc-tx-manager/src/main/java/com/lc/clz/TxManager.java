package com.lc.clz;


import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * 事务管理器
 */
@SpringBootApplication
@EnableEurekaClient  //服务客户端，让该服务注册到Eureka中
@EnableTransactionManagerServer
public class TxManager {

    public static void main(String[] args) {
        SpringApplication.run(TxManager.class, args);
    }
}
