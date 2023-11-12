package com.bottega.vendor.concert.fixtures.clients;

import java.time.*;

import com.bottega.sharedlib.config.TestClockConfig;
import com.bottega.vendor.concert.domain.ConcertId;
import com.bottega.vendor.fixtures.TestBuilders;
import io.restassured.response.ValidatableResponse;
import lombok.*;
import org.apache.groovy.util.Maps;
import org.springframework.stereotype.Component;
import static java.time.ZoneOffset.UTC;

@Component
@RequiredArgsConstructor
public class ConcertHttpClient {

    private final TestBuilders builders;

    @Builder
    public static class ConcertRequest {
        @Builder.Default
        public String title = "default-mock-title";

        @Builder.Default
        public Instant date = TestClockConfig.TEST_TIME_PLUS_30_DAYS;

        @Builder.Default
        public String vendorId = "default-vendor-id";
    }

    public ValidatableResponse createConcert(ConcertRequest concertRequest) {
        return builders.aRequestSpec()
                .body(Maps.of(
                        "title", concertRequest.title,
                        "date", LocalDate.ofInstant(concertRequest.date, UTC).atStartOfDay().toLocalDate().toString(),
                        "vendorId", concertRequest.vendorId
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
