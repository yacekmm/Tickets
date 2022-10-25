package com.bottega.vendor.concert.tests.clients;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import org.apache.groovy.util.Maps;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConcertApiClient {

    private final RequestSpecification requestSpec;

    public ValidatableResponse createConcert(String title, String dateTime, String vendorId) {
        return requestSpec
                .body(Maps.of(
                        "title", title,
                        "dateTime", dateTime,
                        "vendorId", vendorId
                ))
                .post("/concert")
                .then();
    }

}
