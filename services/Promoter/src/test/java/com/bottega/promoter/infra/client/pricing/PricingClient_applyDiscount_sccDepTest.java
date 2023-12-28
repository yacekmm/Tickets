package com.bottega.promoter.infra.client.pricing;

import java.util.List;

import com.bottega.promoter.concert.Price;
import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.promoter.fixtures.*;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PricingClient_applyDiscount_sccDepTest extends SccFrameworkTestBase {

    FakePricingClient fakePricingClient;
    PricingClient realPricingClient;

    @BeforeEach
    public void setUp() {
        fakePricingClient = new FakePricingClient();
        realPricingClient = new HttpPricingClient(sccPricingWebClient);
    }

    @Test
    public void fakeApplyDiscount_isValid() {
        //given
        ConcertId concertId = new ConcertId();

        //when
        Either<ErrorResult, List<Price>> realResult = realPricingClient.applyPercentageDiscount(concertId, 10);
        Either<ErrorResult, List<Price>> fakeResult = fakePricingClient.applyPercentageDiscount(concertId, 10);

        //then
        assertThat(fakeResult.get()).containsExactlyInAnyOrderElementsOf(realResult.get());
    }

}
