package com.bottega.vendor.concert.application.api.dto;

import com.bottega.sharedlib.vo.error.ErrorCode;

public enum ConcertErrorCode implements ErrorCode {

    invalid_date,
    concert_not_found,
    http_error;

}
