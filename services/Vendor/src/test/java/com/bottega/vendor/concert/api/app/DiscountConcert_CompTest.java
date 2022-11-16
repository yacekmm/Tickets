package com.bottega.vendor.concert.api.app;

import com.bottega.sharedlib.fixtures.ErrorAssert;
import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.concert.Price;
import com.bottega.vendor.concert.fixtures.ConcertLogicTestBase;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.bottega.sharedlib.vo.error.ErrorType.NOT_FOUND;
import static com.bottega.sharedlib.vo.error.GenericErrorCode.not_found;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class DiscountConcert_CompTest extends ConcertLogicTestBase {


    @Test
    void discountConcert_discountsConcert_onValidInput() {
        //given

        //when

        //then

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
