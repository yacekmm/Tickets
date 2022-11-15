package com.bottega.vendor.fixtures;

import com.bottega.sharedlib.config.ApiVersions;
import com.bottega.vendor.agreements.fixtures.VendorAgreementBuilder;
import com.bottega.vendor.concert.fixtures.*;
import com.bottega.vendor.concert.infra.repo.ConcertRepo;
import com.bottega.vendor.concertRead.ConcertFinderRepo;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Clock;

import static io.restassured.http.ContentType.JSON;

@Component
@RequiredArgsConstructor
public class TestBuilders {

    private final ConcertRepo concertRepo;
    private final ConcertFinderRepo concertFinderRepo;
    private final Clock clock;

    @Value("${server.port}")
    private int port;

    public DontLook dontLook() {
        return new DontLook(concertRepo, concertFinderRepo, clock);
    }

    public VendorAgreementBuilder aVendorAgreement() {
        return new VendorAgreementBuilder();
    }

    public RequestSpecification aRequestSpec(){
        RestAssured.reset();
        RestAssured.port = port;

        return RestAssured.given()
                .basePath(ApiVersions.V1)
                .contentType(JSON);
    }

    public ConcertBuilder aConcert(){
        return new ConcertBuilder(clock);
    }
}
