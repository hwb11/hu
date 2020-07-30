package com.zy.intelligentdevice.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.zy"})
@MapperScan("com.zy.intelligentdevice.db")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
