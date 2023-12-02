package com.bottega.promoter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.bottega")
public class PromoterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PromoterApplication.class, args);
    }

}
