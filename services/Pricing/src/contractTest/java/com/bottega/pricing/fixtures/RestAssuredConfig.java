package com.bottega.pricing.fixtures;

import com.bottega.sharedlib.config.ApiVersions;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

@Configuration
public class RestAssuredConfig {

    @Bean
    public RequestSpecification requestSpec(@Value("${server.port}") int port){
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(ALL);

        return RestAssured.given()
                .basePath(ApiVersions.V1)
                .contentType(JSON);
    }

}
