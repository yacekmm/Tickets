package com.bottega.vendor.concert.domain;

import com.bottega.vendor.contract.VendorId;
import com.bottega.vendor.shared.error.ErrorResult;
import com.bottega.vendor.tests.UnitTestBase;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.bottega.vendor.config.TestClockConfig.TEST_TIME_PLUS_30_DAYS;
import static org.assertj.core.api.Assertions.assertThat;

class Concert_UnitTest extends UnitTestBase {

    @BeforeEach
    void setUp() {
        ConcertFixtures concertFixtures = ConcertFixtures.init();
    }

    @Test
    void createConcert_createsConcert_onValidInput() {

        //given
        VendorId vendorId = new VendorId();

        //when
        Either<ErrorResult, Concert> result = Concert.createConcert("Woodstock", TEST_TIME_PLUS_30_DAYS.toString(), vendorId);

        //then
        assertThat(result.isRight()).isTrue();
        assertThat(result.get().getId().getValue()).isNotBlank();
        assertThat(result.get().getTitle().getValue()).isEqualTo("Woodstock");
        assertThat(result.get().getDate().getDateTime()).isEqualTo(TEST_TIME_PLUS_30_DAYS);
        assertThat(result.get().getVendorId()).isEqualTo(vendorId);
    }
}