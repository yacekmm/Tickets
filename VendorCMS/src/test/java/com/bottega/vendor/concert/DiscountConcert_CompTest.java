package com.bottega.vendor.concert;

import com.bottega.sharedlib.vo.Money;
import com.bottega.sharedlib.vo.error.ErrorAssert;
import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.concert.tests.asserts.PriceAssert;
import io.vavr.control.Either;
import org.apache.groovy.util.Maps;
import org.junit.jupiter.api.Test;

import static com.bottega.sharedlib.vo.error.ErrorType.NOT_FOUND;
import static com.bottega.vendor.concert.application.api.dto.ConcertErrorCode.concert_not_found;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class DiscountConcert_CompTest extends ConcertUnitTestBase {


    @Test
    void discountConcert_discountsConcert_onValidInput() {
        //given
        Concert concert = concertFixtures.concertBuilder.inDb();

        //when
        Either<ErrorResult, Price> result = concertFixtures.concertService.discountConcert(concert.getId().asString(), 10);

        //then
        assertThat(result).hasRightValueSatisfying(price ->
                PriceAssert.assertThatPrice(price)
                        .is(new Money(90_00))
                        .hasFactors(new PriceFactor("PERCENTAGE", Maps.of("value", "10"))));
    }

    @Test
    void discountConcert_returnsError_onInvalidConcertId() {

        //when
        Either<ErrorResult, Price> result = concertFixtures.concertService.discountConcert("not-existing", 10);

        //then
        assertThat(result).hasLeftValueSatisfying(error ->
                ErrorAssert.assertThatError(error)
                        .hasType(NOT_FOUND)
                        .hasCode(concert_not_found)
                        .hasDescription("Concert with given ID does not exist. ID: not-existing"));
    }

}
