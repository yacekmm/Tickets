package com.bottega.sharedlib.config;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

@Configuration
public class RestAssuredConfig {

    @Bean
    public RequestSpecification defaultRequestSpec(@Value("${server.port}") int port){
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(ALL);

        return RestAssured.given()
                .basePath(ApiVersions.V1)
                .contentType(JSON);
    }
}
