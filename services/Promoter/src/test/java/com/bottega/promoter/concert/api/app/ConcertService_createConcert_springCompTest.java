package com.bottega.promoter.concert.api.app;

import com.bottega.promoter.concert.fixtures.asserts.ConcertAssert;
import com.bottega.promoter.concert.fixtures.clients.ConcertHttpClient;
import com.bottega.promoter.fixtures.FrameworkTestBase;
import com.bottega.sharedlib.fixtures.RepoEntries;
import org.assertj.vavr.api.VavrAssertions;
import org.junit.jupiter.api.Test;
import static com.bottega.promoter.concert.fixtures.asserts.ConcertAssert.assertThatConcert;
import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;


class ConcertService_createConcert_springCompTest extends FrameworkTestBase {

    @Test
    void createConcert_OK() {
        //given
        ConcertHttpClient.ConcertRequest request = ConcertHttpClient.ConcertRequest.builder().build();

        //when
        var result = concertFixtures.concertService.createConcert(
                request.title, request.date.toString(), request.promoterId);

        //then
        VavrAssertions.assertThat(result).isRight();

        assertThatConcert(concertFixtures.concertRepo.findAll().iterator().next())
                .isPersistedIn(concertFixtures.concertRepo, RepoEntries.SINGULAR)
                .hasTitle(request.title)
                .hasIdAsUUID()
                .hasDate(TEST_TIME_PLUS_30_DAYS)
                .hasPromoterId(request.promoterId);
    }

}
