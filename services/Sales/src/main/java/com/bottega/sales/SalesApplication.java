package com.bottega.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.bottega")
public class SalesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }

}
