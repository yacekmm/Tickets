package com.bottega.vendor.infra.client.pricing;

import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.concert.Price;
import com.bottega.vendor.concert.domain.ConcertId;
import com.bottega.vendor.fixtures.*;
import io.vavr.control.Either;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.vavr.api.VavrAssertions.assertThat;

public class DiscountConcertClient_DepTest extends FrameworkTestBase {

    @Autowired
    HttpPricingClient httpPricingClient;

    FakePricingClient fakePricingClient;

    @BeforeEach
    void setUp() {
        fakePricingClient = new FakePricingClient();
    }

    @Test
    public void fakeApplyDiscountClient_isValid() {
        //given
        ConcertId concertId = new ConcertId();

        //when
        Either<ErrorResult, List<Price>> httpResult = httpPricingClient.applyPercentageDiscount(concertId, 10);
        Either<ErrorResult, List<Price>> fakeResult = fakePricingClient.applyPercentageDiscount(concertId, 10);

        //then
        assertThat(httpResult).isRight();
        assertThat(fakeResult).isRight();
        assertThat(fakeResult).hasRightValueSatisfying(prices -> {
            Assertions.assertThat(prices).containsExactlyInAnyOrder(httpResult.get().toArray(new Price[]{}));
        });

    }

}
