package com.bottega.pricing.fixtures;

import com.bottega.sharedlib.config.ServicesProperties;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.filter.log.LogDetail.ALL;

public class CdcFrameworkTestBase extends FrameworkTestBase {

    @Autowired
    ServicesProperties servicesProperties;

    @BeforeEach
    void setUp() {
        RestAssured.port = servicesProperties.getPricing().port();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(ALL);
    }
}
