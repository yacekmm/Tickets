package com.bottega.vendor.concert.api.app;

import com.bottega.sharedlib.fixtures.RepoEntries;
import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.agreements.VendorId;
import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.concert.fixtures.ConcertLogicTestBase;
import com.bottega.vendor.concert.fixtures.asserts.ConcertAssert;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class CreateConcert_CompTest extends ConcertLogicTestBase {


    @Test
    void createConcert_createsConcert_onValidInput() {
        //when
        Either<ErrorResult, Concert> result = concertFixtures.concertService.createConcert("Woodstock 2000", TEST_TIME_PLUS_30_DAYS.toString(), "vendor-id");

        //then
        assertThat(result).isRight();
        ConcertAssert.assertThatConcert(result.get())
                .hasIdAsUUID()
                .hasTitle("Woodstock 2000")
                .hasDate(TEST_TIME_PLUS_30_DAYS)
                .hasVendorId(new VendorId("vendor-id"))
                .hasTags(Set.of());
    }

    @Test
    void createConcert_persistsConcert_onValidInput() {
        //when
        Either<ErrorResult, Concert> result = concertFixtures.concertService.createConcert("Woodstock 2000", TEST_TIME_PLUS_30_DAYS.toString(), "vendor-id");

        //then
        assertThat(result).isRight();
        ConcertAssert.assertThatConcert(result.get())
                .isPersistedIn(concertFixtures.concertRepo, RepoEntries.SINGULAR);
    }


}
