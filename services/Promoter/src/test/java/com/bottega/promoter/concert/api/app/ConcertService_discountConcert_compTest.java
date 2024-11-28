package com.bottega.promoter.concert.api.app;

import com.bottega.promoter.concert.Price;
import com.bottega.promoter.concert.domain.Concert;
import com.bottega.promoter.concert.fixtures.ConcertLogicTestBase;
import com.bottega.sharedlib.vo.Money;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.bottega.promoter.concert.fixtures.asserts.PriceAssert.assertThatPrice;
import static com.bottega.sharedlib.fixtures.ErrorAssert.assertThatError;
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
            assertThatPrice(result.get().getFirst())
                    .equalTo(new Money(90_00));

    }

    @Test
    void discountConcert_returnsError_onInvalidConcertId() {

        //when
        Either<ErrorResult, List<Price>> result = concertFixtures.concertService.discountConcert("not-existing", 10);

        //then
        assertThat(result).hasLeftValueSatisfying(error ->
                assertThatError(error)
                        .hasType(NOT_FOUND)
                        .hasCode(not_found)
                        .hasDescription("Concert with given ID does not exist. ID: not-existing"));
    }

}
