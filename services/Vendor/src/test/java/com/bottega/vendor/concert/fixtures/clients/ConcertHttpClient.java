package com.bottega.vendor.concert.fixtures.clients;

import com.bottega.vendor.concert.domain.ConcertId;
import com.bottega.vendor.fixtures.TestBuilders;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import org.apache.groovy.util.Maps;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConcertHttpClient {

    private final TestBuilders builders;

    public ValidatableResponse notImportant(String title, String date, String vendorId) {
        return builders.aRequestSpec()
                .body(Maps.of(
                        "title", title,
                        "date", date,
                        "vendorId", vendorId
                ))
                .post("/concert")
                .then();
    }

    public ValidatableResponse discountConcert(ConcertId concertId, int percentage) {
        return builders.aRequestSpec()
                .body(Maps.of(
                        "percentage", percentage
                ))
                .pathParam("concert-id", concertId.asString())
                .post("/concert/{concert-id}/discount")
                .then();
    }

    public ValidatableResponse findConcertsForVendor(String vendorId) {
        return builders.aRequestSpec()
                .pathParam("vendor-id", vendorId)
                .get("/concert/vendor/{vendor-id}")
                .then();
    }
}
