package com.bottega.sharedlib.vo.error;

import lombok.Getter;

public class ErrorException extends RuntimeException {

    @Getter
    private final ErrorResult error;

    public ErrorException(ErrorResult error) {
        super(error.toString());
        this.error = error;
    }
}
