package com.bottega.vendor.concert;

import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.shared.error.ErrorResult;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import static com.bottega.vendor.tests.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class ConcertService_CompTest extends ConcertTestBase {


    @Test
    void createConcert_createsConcert_onValidInput() {

        //when
        Either<ErrorResult, Concert> result = concertFixtures.concertService.createConcert("Woodstock", TEST_TIME_PLUS_30_DAYS.toString(), "vendorId");

        //then
        assertThat(result).isRight();
        assertThat(result.get().getId().getValue()).isNotBlank();
        assertThat(result.get().getTitle().getValue()).isEqualTo("Woodstock");
        assertThat(result.get().getDate().getDateTime()).isEqualTo(TEST_TIME_PLUS_30_DAYS);
        assertThat(result.get().vendorId().asString()).isEqualTo("vendorId");
    }

    @Test
    void createConcert_persistsConcert_onValidInput() {

        //when
        Either<ErrorResult, Concert> result = concertFixtures.concertService.createConcert("Woodstock", TEST_TIME_PLUS_30_DAYS.toString(), "vendorId");

        //then
        assertThat(concertFixtures.concertRepo.findById(result.get().getId())).hasValueSatisfying(
                actual -> assertThat(actual).isEqualTo(result.get()));

        assertThat(concertFixtures.concertRepo.findAll()).hasSize(1);
    }
}
