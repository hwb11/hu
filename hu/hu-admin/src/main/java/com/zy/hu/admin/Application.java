package com.zy.hu.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.zy"})
@MapperScan("com.zy.hu.db")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
