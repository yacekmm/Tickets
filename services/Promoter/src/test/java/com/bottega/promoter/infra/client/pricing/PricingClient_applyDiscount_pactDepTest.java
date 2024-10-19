package com.bottega.promoter.infra.client.pricing;

import com.bottega.promoter.concert.Price;
import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.promoter.fixtures.FrameworkTestBase;
import com.bottega.sharedlib.vo.Money;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.bottega.promoter.concert.fixtures.asserts.PriceAssert.assertThatPrice;

public class PricingClient_applyDiscount_pactDepTest extends FrameworkTestBase {

    @Test
    public void applyDiscount_isValid() {
        //given
        ConcertId concertId = new ConcertId("123");

        //when
        Either<ErrorResult, List<Price>> result = concertFixtures.pricingClient.applyPercentageDiscount(concertId, 10);

        //then
        assertThatPrice(result.get().get(0))
                .equalTo(new Money(90_00));
    }

}
