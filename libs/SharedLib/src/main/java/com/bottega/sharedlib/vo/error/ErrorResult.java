package com.bottega.sharedlib.vo.error;

import lombok.*;

import static lombok.AccessLevel.PRIVATE;

@Getter
@ToString
@AllArgsConstructor(access = PRIVATE)
//TODO: test it
public class ErrorResult {

    private final ErrorType type;
    private final ErrorCode code;
    private final String description;

    public static ErrorResult badRequest(ErrorCode code, String description) {
        return new ErrorResult(ErrorType.BAD_REQUEST, code, description);
    }

    public static ErrorResult unauthorized(ErrorCode code, String description) {
        return new ErrorResult(ErrorType.UNAUTHORIZED, code, description);
    }

    public static ErrorResult forbidden(ErrorCode code, String description) {
        return new ErrorResult(ErrorType.FORBIDDEN, code, description);
    }

    public static ErrorResult notFound(ErrorCode code, String description) {
        return new ErrorResult(ErrorType.NOT_FOUND, code, description);
    }

    public static ErrorResult internalError(ErrorCode code, String description) {
        return new ErrorResult(ErrorType.INTERNAL_ERROR, code, description);
    }

    public static ErrorResult serviceUnavailable(ErrorCode code, String description) {
        return new ErrorResult(ErrorType.SERVICE_UNAVAILABLE, code, description);
    }

    //TODO: test
    public ErrorException toException() {
        return new ErrorException(this);
    }
}
