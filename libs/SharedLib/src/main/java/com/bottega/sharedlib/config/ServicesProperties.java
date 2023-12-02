package com.bottega.sharedlib.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("services")
@Getter
@Setter
public class ServicesProperties {

    private int requestTimeoutInSeconds;

    private ServiceConfig promoter;
    private ServiceConfig pricing;

    public record ServiceConfig(
            String url,
            int port
    ){ }
}
