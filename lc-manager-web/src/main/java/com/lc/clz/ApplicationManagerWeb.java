package com.lc.clz;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ApplicationManagerWeb {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationManagerWeb.class).run(args);
    }
}
