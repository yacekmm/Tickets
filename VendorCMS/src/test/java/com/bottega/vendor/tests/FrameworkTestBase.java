package com.bottega.vendor.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT, properties = "server.port=9090")
public class FrameworkTestBase {

    @BeforeEach
    void setUp() {
        setupRestClient();
    }

    private static void setupRestClient() {
        RestAssured.port = 9090;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }
}
