package com.bottega.vendor.concert.fixtures.clients;

import com.bottega.vendor.concert.domain.ConcertId;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import org.apache.groovy.util.Maps;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConcertApiClient {

    private final RequestSpecification requestSpec;

    public ValidatableResponse createConcert(String title, String date, String vendorId) {
        return requestSpec
                .body(Maps.of(
                        "title", title,
                        "date", date,
                        "vendorId", vendorId
                ))
                .post("/concert")
                .then();
    }

    public ValidatableResponse discountConcert(ConcertId concertId, int percentage) {
        return requestSpec
                .body(Maps.of(
                        "percentage", percentage
                ))
                .post("/concert/{concert-id}/discount", concertId.asString())
                .then();
    }

    public ValidatableResponse findConcertsForVendor(String vendorId) {
        return requestSpec
                .get("/concert/vendor/" + vendorId)
                .then();
    }
}
