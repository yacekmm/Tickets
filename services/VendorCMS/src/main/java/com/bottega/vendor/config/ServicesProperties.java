package com.bottega.vendor.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("services")
@Getter
@Setter
public class ServicesProperties {

    private int requestTimeoutInSeconds;

    private ServiceConfig pricing;

    public record ServiceConfig(
            String url
    ){ }
}
