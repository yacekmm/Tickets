package com.bottega.promoter.pricing.api.app;

import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.promoter.concert.fixtures.ConcertLogicTestBase;
import com.bottega.promoter.pricing.infra.PriceRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.bottega.promoter.concert.fixtures.asserts.PriceAssert.assertThatPrice;

class PricingService_liveCoding_Test extends ConcertLogicTestBase {

    private PricingService pricingService;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();
        pricingService = new PricingService(new PriceRepo());
    }

    @Test
    void applyPercentageDiscount_returnsDiscountedPrice() {
        //when
        var result = pricingService.applyPercentageDiscount(new ConcertId("123"), 10);

        //then
        assertThatPrice(result.get().getFirst())
                .equalTo(90_00)
                .hasPercentageFactor(10);
    }
}