package com.bottega.vendor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.bottega")
public class VendorCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendorCmsApplication.class, args);
    }

}
