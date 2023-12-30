package com.bottega.promoter.concert.fixtures.clients;

import java.time.*;

import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.promoter.fixtures.TestBuilders;
import com.bottega.sharedlib.config.TestClockConfig;
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
        public String promoterId = "default-promoter-id";
    }

    public ValidatableResponse createConcert(ConcertRequest concertRequest) {
        return builders.aRequestSpec()
                .body(Maps.of(
                        //TODO bug here might cause tests to fail. check logs
                        "date", LocalDate.ofInstant(concertRequest.date, UTC).atStartOfDay().toLocalDate().toString(),
                        "promoterId", concertRequest.promoterId
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

    public ValidatableResponse findConcertsForPromoter(String promoterId) {
        return builders.aRequestSpec()
                .pathParam("promoter-id", promoterId)
                .get("/concert/promoter/{promoter-id}")
                .then();
    }
}
