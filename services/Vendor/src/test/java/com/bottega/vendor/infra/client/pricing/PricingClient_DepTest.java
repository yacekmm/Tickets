package com.bottega.vendor.infra.client.pricing;

import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.concert.Price;
import com.bottega.vendor.concert.domain.ConcertId;
import com.bottega.vendor.fixtures.*;
import io.vavr.control.Either;
import org.junit.jupiter.api.*;

import java.util.List;

public class PricingClient_DepTest extends FrameworkTestBase {

    FakePricingClient fakePricingClient;
    PricingClient realPricingClient;

    @BeforeEach
    void setUp() {
        fakePricingClient = new FakePricingClient();
        realPricingClient = concertFixtures.pricingClient;
    }

    @Test
    public void fakeApplyDiscountClient_isValid() {
        //given
        ConcertId concertId = new ConcertId();

        //when
        Either<ErrorResult, List<Price>> realResult = realPricingClient.applyPercentageDiscount(concertId, 10);
        Either<ErrorResult, List<Price>> fakeResult = fakePricingClient.applyPercentageDiscount(concertId, 10);

        //then

    }

}
