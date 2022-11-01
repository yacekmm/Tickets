package com.bottega.vendor.concert.api.app;

import com.bottega.sharedlib.fixtures.EventAssert;
import com.bottega.sharedlib.fixtures.RepoEntries;
import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.concert.fixtures.ConcertLogicTestBase;
import com.bottega.vendor.concert.fixtures.asserts.ConcertAssert;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class CreateConcert_CompTest extends ConcertLogicTestBase {


    @Test
    void createConcert_createsConcert_onValidInput() {

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

    @Test
    void createConcert_publishesEvent_onValidInput() {

        //when
        Either<ErrorResult, Concert> result = concertFixtures.concertService.createConcert("Woodstock", TEST_TIME_PLUS_30_DAYS.toString(), "vendorId");

        //then
        assertThat(result).hasRightValueSatisfying(c ->
                EventAssert.assertThatEventV1(sharedFixtures.fakeEventPublisher().singleEvent())
                        .isConcertCreated(
                                c.getId().asString(),
                                c.getTitle().getValue(),
                                c.getDate().getDate().toString(),
                                new String[]{},
                                5)
        );
    }

}
