package com.bottega.promoter.infra.client.pricing;

import java.util.List;

import com.bottega.promoter.concert.Price;
import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.promoter.fixtures.*;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PricingClient_applyDiscount_pactDepTest extends PactFrameworkTestBase {

    FakePricingClient fakePricingClient;
    PricingClient realPricingClient;

    @BeforeEach
    void setUp() {
        fakePricingClient = new FakePricingClient();
        realPricingClient = concertFixtures.pricingClient;
    }

    @Test
    public void fakeApplyDiscount_isValid() {
        //given
        ConcertId concertId = new ConcertId("does contract expect something specific?");

        //when
        //TODO: get realResult from realPricingClient
        Either<ErrorResult, List<Price>> fakeResult = fakePricingClient.applyPercentageDiscount(concertId, 10);

        //then
        //TODO assert that real and fake results are equal
    }

}
