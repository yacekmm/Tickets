package com.bottega.vendor.concert.api.app;

import com.bottega.sharedlib.fixtures.*;
import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.vendor.agreements.VendorAgreement;
import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.concert.fixtures.ConcertLogicTestBase;
import com.bottega.vendor.concert.fixtures.asserts.ConcertAssert;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.bottega.sharedlib.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

class ConcertService_CreateConcert_CompTest extends ConcertLogicTestBase {


    @Test
    void createConcert_createsConcert_onValidInput() {
        //given
        VendorAgreement vendorAgreement = builders.aVendorAgreement().forVendor("vendorId").build();
        given(concertFixtures.vendorService.getVendorAgreement("vendorId")).willReturn(vendorAgreement);

        //when
        Either<ErrorResult, Concert> result = concertFixtures.concertService.createConcert("Woodstock 2000", TEST_TIME_PLUS_30_DAYS.toString(), vendorAgreement.vendorId().asString());

        //then
        assertThat(result).isRight();
        ConcertAssert.assertThatConcert(result.get())
                .hasIdAsUUID()
                .hasTitle("Woodstock 2000")
                .hasDate(TEST_TIME_PLUS_30_DAYS)
                .hasVendorId(vendorAgreement.vendorId())
                .hasTags(Set.of());
    }

    @Test
    void createConcert_persistsConcert_onValidInput() {
        //given
        VendorAgreement vendorAgreement = builders.aVendorAgreement().build();
        given(concertFixtures.vendorService.getVendorAgreement(anyString())).willReturn(vendorAgreement);

        //when
        Either<ErrorResult, Concert> result = concertFixtures.concertService.createConcert("Woodstock 2000", TEST_TIME_PLUS_30_DAYS.toString(), vendorAgreement.vendorId().asString());

        //then
        assertThat(result).isRight();
        ConcertAssert.assertThatConcert(result.get())
                .isPersistedIn(concertFixtures.concertRepo, RepoEntries.SINGULAR);
    }

    @Test
    void createConcert_returnsError_onVendorIdNotFound() {
        //when
        Either<ErrorResult, Concert> result = concertFixtures.concertService.createConcert("Woodstock 2000", TEST_TIME_PLUS_30_DAYS.toString(), "non-existing");

        //then
        assertThat(result).isLeft();
    }

    @Test
    void createConcert_publishesEvent_onValidInput() {
        //given
        VendorAgreement vendorAgreement = builders.aVendorAgreement().build();
        given(concertFixtures.vendorService.getVendorAgreement(anyString())).willReturn(vendorAgreement);

        //when
        Either<ErrorResult, Concert> result = concertFixtures.concertService.createConcert("Woodstock 2000", TEST_TIME_PLUS_30_DAYS.toString(), vendorAgreement.vendorId().asString());

        //then
        assertThat(result).hasRightValueSatisfying(concert ->
                EventAssert.assertThatEventV1(sharedFixtures.fakeEventPublisher().singleEvent())
                        .isConcertCreated(
                                concert.getId().asString(),
                                concert.getTitle().getValue(),
                                concert.getDate().getUtcDate().toString(),
                                new String[]{},
                                vendorAgreement.profitMarginPercentage())
        );
    }

}
