package com.bottega.vendor.concert;

import com.bottega.vendor.concert.domain.Concert;
import com.bottega.vendor.contract.VendorId;
import com.bottega.vendor.shared.error.ErrorResult;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import static com.bottega.vendor.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;
import static org.assertj.core.api.Assertions.assertThat;

class ConcertService_CompTest extends ConcertTestBase {


    @Test
    void createConcert_createsConcert_onValidInput() {

        //given
        VendorId vendorId = new VendorId();

        //when
        Either<ErrorResult, Concert> result = concertFixtures.concertService.createConcert("Woodstock", TEST_TIME_PLUS_30_DAYS.toString(), vendorId);

        //then
        assertThat(result.isRight()).isTrue();
        assertThat(result.get().getId().getValue()).isNotBlank();
        assertThat(result.get().getTitle().getValue()).isEqualTo("Woodstock");
        assertThat(result.get().getDate().getDateTime()).isEqualTo(TEST_TIME_PLUS_30_DAYS);
        assertThat(result.get().getVendorId()).isEqualTo(vendorId);
    }

    @Test
    void createConcert_persistsConcert_onValidInput() {

        //when
        Either<ErrorResult, Concert> result = concertFixtures.concertService.createConcert("Woodstock", TEST_TIME_PLUS_30_DAYS.toString(), new VendorId());

        //then
        assertThat(concertFixtures.concertRepo.findById(result.get().getId())).hasValueSatisfying(
                actual -> assertThat(actual).isEqualTo(result.get()));

        assertThat(concertFixtures.concertRepo.findAll()).hasSize(1);
    }
}
