package com.bottega.vendor.concert.api.app;

import com.bottega.sharedlib.vo.error.ErrorCode;

public enum ConcertErrorCode implements ErrorCode {

    invalid_date,
    invalid_title,
    concert_not_found,
    http_error;

}
