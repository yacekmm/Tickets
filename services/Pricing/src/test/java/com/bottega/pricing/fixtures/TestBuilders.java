package com.bottega.pricing.fixtures;

import com.bottega.pricing.price.fixtures.DontLook;
import com.bottega.pricing.price.infra.repo.ItemPriceRepo;
import com.bottega.sharedlib.config.ApiVersions;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static io.restassured.http.ContentType.JSON;

@Component
@RequiredArgsConstructor
public class TestBuilders {

    private final ItemPriceRepo priceRepo;

    @Value("${server.port}")
    private int port;

    public DontLook dontLook() {
        return new DontLook(priceRepo);
    }

    public RequestSpecification aRequestSpec() {
        RestAssured.reset();
        RestAssured.port = port;

        return RestAssured.given()
                .basePath(ApiVersions.V1)
                .contentType(JSON);
    }
}
