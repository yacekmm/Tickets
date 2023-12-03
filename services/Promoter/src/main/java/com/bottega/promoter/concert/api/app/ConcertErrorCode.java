package com.bottega.promoter.concert.api.app;

import com.bottega.sharedlib.vo.error.ErrorCode;

public enum ConcertErrorCode implements ErrorCode {

    invalid_date,
    invalid_title,
    http_error;

}
