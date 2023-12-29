package com.bottega.promoter.concert.fixtures;

import com.bottega.promoter.concert.domain.ConcertDate;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Validation;
import lombok.RequiredArgsConstructor;
import static com.bottega.promoter.concert.api.app.ConcertErrorCode.invalid_date;
import static com.bottega.sharedlib.vo.error.ErrorType.BAD_REQUEST;
import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

@RequiredArgsConstructor
public class ConcertDateAssert {

    //TODO implement
}
