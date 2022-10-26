package com.bottega.pricing.tests;

import com.bottega.sharedlib.config.ApiVersions;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

@Configuration
public class PricingRestAssured extends RestAssured {

    @Bean
    public RequestSpecification defaultRequestSpec(){
        RestAssured.port = 9091;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(ALL);

        return RestAssured.given()
                .basePath(ApiVersions.V1)
                .contentType(JSON);
    }
}
