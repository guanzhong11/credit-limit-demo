package com.demo.creditlimit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {
        "com.demo.creditlimit"
})
public class CreditLimitApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditLimitApplication.class, args);
    }

}
