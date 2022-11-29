package com.bottega.vendor.concert.api.app;

import com.bottega.sharedlib.fixtures.RepoEntries;
import com.bottega.vendor.concert.fixtures.asserts.ConcertAssert;
import com.bottega.vendor.fixtures.FrameworkTestBase;
import org.junit.jupiter.api.Test;

import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;
import static com.bottega.vendor.concert.fixtures.clients.ConcertHttpClient.ConcertRequest;
import static org.assertj.vavr.api.VavrAssertions.assertThat;


class ConcertService_CreateConcert_SpringCompTest extends FrameworkTestBase {

    @Test
    void createConcert_OK() {
        //given
        ConcertRequest request = ConcertRequest.builder().build();

        //when
        var result = concertFixtures.concertService.createConcert(
                request.title, request.date.toString(), request.vendorId);

        //then
        assertThat(result).isRight();

        ConcertAssert
                .assertThatConcert(concertFixtures.concertRepo.findAll().iterator().next())
                .isPersistedIn(concertFixtures.concertRepo, RepoEntries.SINGULAR)
                .hasTitle(request.title)
                .hasIdAsUUID()
                .hasDate(TEST_TIME_PLUS_30_DAYS)
                .hasVendorId(request.vendorId);
    }

}
