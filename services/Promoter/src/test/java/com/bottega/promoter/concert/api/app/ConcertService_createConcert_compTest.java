package com.bottega.promoter.concert.api.app;

import com.bottega.promoter.agreements.PromoterAgreement;
import com.bottega.promoter.concert.domain.Concert;
import com.bottega.promoter.concert.fixtures.ConcertLogicTestBase;
import com.bottega.promoter.concert.fixtures.asserts.ConcertAssert;
import com.bottega.promoter.concert.fixtures.clients.ConcertHttpClient;
import com.bottega.sharedlib.fixtures.*;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import org.assertj.vavr.api.VavrAssertions;
import org.junit.jupiter.api.Test;
import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;


class ConcertService_createConcert_compTest extends ConcertLogicTestBase {

    @Test
    void createConcert_OK() {
        //given
        ConcertHttpClient.ConcertRequest request = ConcertHttpClient.ConcertRequest.builder().build();
        given(concertFixtures.promoterService.getPromoterAgreement(anyString()))
                .willReturn(builders.aPromoterAgreement().forPromoter(request.promoterId).build());

        //when
        var result = concertFixtures.concertService.createConcert(
                request.title, request.date.toString(), request.promoterId);

        //then
        VavrAssertions.assertThat(result).isRight();

        ConcertAssert
                .assertThatConcert(concertFixtures.concertRepo.findAll().iterator().next())
                .isPersistedIn(concertFixtures.concertRepo, RepoEntries.SINGULAR)
                .hasTitle(request.title)
                .hasIdAsUUID()
                .hasDate(TEST_TIME_PLUS_30_DAYS)
                .hasPromoterId(request.promoterId);
    }

    @Test
    void createConcert_persistsConcert_onValidInput() {
        //given
        PromoterAgreement promoterAgreement = builders.aPromoterAgreement().build();
        given(concertFixtures.promoterService.getPromoterAgreement(anyString())).willReturn(promoterAgreement);

        //when
        Either<ErrorResult, Concert> result = concertFixtures.concertService.createConcert("Woodstock 2000", TEST_TIME_PLUS_30_DAYS.toString(), promoterAgreement.promoterId().asString());

        //then
        assertThat(result).isRight();
        ConcertAssert.assertThatConcert(result.get())
                .isPersistedIn(concertFixtures.concertRepo, RepoEntries.SINGULAR);
    }

    @Test
    void createConcert_returnsError_onPromoterIdNotFound() {
        //when
        Either<ErrorResult, Concert> result = concertFixtures.concertService.createConcert("Woodstock 2000", TEST_TIME_PLUS_30_DAYS.toString(), "non-existing");

        //then
        assertThat(result).isLeft();
    }

    @Test
    void createConcert_publishesEvent_onValidInput() {
        //given
        PromoterAgreement promoterAgreement = builders.aPromoterAgreement().build();
        given(concertFixtures.promoterService.getPromoterAgreement(anyString())).willReturn(promoterAgreement);

        //when
        Either<ErrorResult, Concert> result = concertFixtures.concertService.createConcert("Woodstock 2000", TEST_TIME_PLUS_30_DAYS.toString(), promoterAgreement.promoterId().asString());

        //then
        assertThat(result).hasRightValueSatisfying(concert ->
                EventAssert.assertThatEventV1(sharedFixtures.fakeEventPublisher().singleEvent())
                        .isConcertCreated(
                                concert.getId().asString(),
                                concert.getTitle().getValue(),
                                concert.getDate().getUtcDate().toString(),
                                new String[]{},
                                promoterAgreement.profitMarginPercentage())
        );
    }

}
