package com.bottega.promoter.infra.client.pricing;

import com.bottega.promoter.concert.Price;
import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.promoter.fixtures.FakePricingClient;
import com.bottega.promoter.fixtures.PactFrameworkTestBase;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PricingClient_applyDiscount_pactDepTest extends PactFrameworkTestBase {

    FakePricingClient fakePricingClient;
    PricingClient pricingHttpClient;

    @BeforeEach
    void setUp() {
        fakePricingClient = new FakePricingClient();
        pricingHttpClient = concertFixtures.pricingClient;
    }

    @Test
    public void fakeApplyDiscount_isValid() {
        //given
        ConcertId concertId = new ConcertId("123");

        //when
        Price result = pricingHttpClient.applyPercentageDiscount(itemId, 10);
        Either<ErrorResult, List<Price>> fakeResult = fakePricingClient.applyPercentageDiscount(concertId, 10);

        //then
        assertThat(fakeResult.get()).containsExactlyInAnyOrderElementsOf(realResult.get());
    }

}
