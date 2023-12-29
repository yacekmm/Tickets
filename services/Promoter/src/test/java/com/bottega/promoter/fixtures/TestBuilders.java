package com.bottega.promoter.fixtures;

import java.time.Clock;

import com.bottega.promoter.concert.fixtures.Builder;
import com.bottega.promoter.concert.infra.repo.ConcertRepo;
import com.bottega.promoter.concertRead.ConcertFinderRepo;
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

    private final ConcertRepo concertRepo;
    private final ConcertFinderRepo concertFinderRepo;
    private final Clock clock;

    @Value("${server.port}")
    private int port;

    public Builder aConcert() {
        return new Builder(concertRepo, concertFinderRepo, clock);
    }

    public PromoterAgreementBuilder aPromoterAgreement() {
        return new PromoterAgreementBuilder();
    }

    public RequestSpecification aRequestSpec(){
        RestAssured.reset();
        RestAssured.port = port;

        return RestAssured.given()
                .basePath(ApiVersions.V1)
                .contentType(JSON);
    }

    //TODO: add method concert() returning new instance of ConcertBuilder
}
