package com.bottega.vendor.concert;

import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.concert.tests.asserts.ConcertAssert;
import com.bottega.vendor.tests.RepoEntries;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import static com.bottega.vendor.tests.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class CreateConcert_CompTest extends ConcertUnitTestBase {


    @Test
    void createConcert_createsConcert_onValidInput() {

        Concert concert = concertFixtures.concertBuilder.inDb();

        //when
        Either<ErrorResult, Concert> result = concertFixtures.concertService.createConcert("Woodstock", TEST_TIME_PLUS_30_DAYS.toString(), "vendorId");

        //then
        assertThat(result).isRight();
        ConcertAssert.assertThatConcert(result.get())
                .hasIdAsUUID()
                .hasTitle("Woodstock")
                .hasDateTime(TEST_TIME_PLUS_30_DAYS)
                .hasVendorId("vendorId")
                .isPersistedIn(concertFixtures.concertRepo, RepoEntries.SINGULAR);
    }

}
