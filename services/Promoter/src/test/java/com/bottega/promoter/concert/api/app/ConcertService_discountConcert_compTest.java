package com.bottega.promoter.concert.api.app;

import java.util.List;

import com.bottega.promoter.concert.*;
import com.bottega.promoter.concert.domain.Concert;
import com.bottega.promoter.concert.fixtures.ConcertLogicTestBase;
import com.bottega.promoter.concert.fixtures.asserts.PriceAssert;
import com.bottega.sharedlib.fixtures.ErrorAssert;
import com.bottega.sharedlib.vo.Money;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static com.bottega.sharedlib.vo.error.ErrorType.NOT_FOUND;
import static com.bottega.sharedlib.vo.error.GenericErrorCode.not_found;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class ConcertService_discountConcert_compTest extends ConcertLogicTestBase {


    @Test
    void discountConcert_discountsConcert_onValidInput() {
        //given
        Concert concert = builders.aConcert().inDb();

        //when
        Either<ErrorResult, List<Price>> result = concertFixtures.concertService.discountConcert(concert.getId().asString(), 10);

        //then
        assertThat(result).hasRightValueSatisfying(prices -> {
            Assertions.assertThat(prices).hasSize(1);
            PriceAssert.assertThatPrice(prices.get(0))
                    .equalTo(new Money(90_00))
                    .hasFactors(new PriceFactor("PERCENTAGE", 10, null));
        });
    }

    @Test
    void discountConcert_returnsError_onInvalidConcertId() {

        //when
        Either<ErrorResult, List<Price>> result = concertFixtures.concertService.discountConcert("not-existing", 10);

        //then
        assertThat(result).hasLeftValueSatisfying(error ->
                ErrorAssert.assertThatError(error)
                        .hasType(NOT_FOUND)
                        .hasCode(not_found)
                        .hasDescription("Concert with given ID does not exist. ID: not-existing"));
    }

}
