package com.bottega.pricing.tests;

import io.restassured.RestAssured;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.filter.log.LogDetail.ALL;

@Ignore("only automatically generated contract test subclasses should be fired")
public class CdcFrameworkTestBase extends FrameworkTestBase {

    @BeforeEach
    void setUp() {
        RestAssured.port = 9091;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(ALL);
    }
}
