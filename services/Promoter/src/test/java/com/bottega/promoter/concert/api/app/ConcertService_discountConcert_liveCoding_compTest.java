package com.bottega.promoter.concert.api.app;

import com.bottega.promoter.concert.fixtures.ConcertLogicTestBase;
import com.bottega.promoter.concert.fixtures.PricingStubs;
import com.bottega.promoter.pricing.api.app.PricingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.bottega.promoter.concert.fixtures.asserts.PriceAssert.assertThatPrice;
import static org.mockito.Mockito.mock;

class ConcertService_discountConcert_liveCoding_compTest extends ConcertLogicTestBase {

    private PricingService pricingServiceMock;
    private PricingStubs pricingStubs;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();
        pricingServiceMock = mock(PricingService.class);
        concertFixtures.concertService.setPricingService(pricingServiceMock);

        pricingStubs = new PricingStubs();
    }

    @Test
    public void applyDiscount_isValid() {
        //given
        builders.aConcert().withId("123").inDb();
        pricingStubs.stubApplyPercentageDiscount(pricingServiceMock);

        //when
        var result = concertFixtures.concertService.discountConcertLiveCoding("123", 10);

        //then
        assertThatPrice(result.get().getFirst())
                .equalTo(90_00)
                .hasPercentageFactor(10);
    }

}
