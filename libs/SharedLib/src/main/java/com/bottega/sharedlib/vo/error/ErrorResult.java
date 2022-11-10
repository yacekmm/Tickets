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

    public static ErrorResult badRequest(ErrorCode code, String description, Object... args) {
        return new ErrorResult(ErrorType.BAD_REQUEST, code, String.format(description, args));
    }

    public static ErrorResult unauthorized(ErrorCode code, String description, Object... args) {
        return new ErrorResult(ErrorType.UNAUTHORIZED, code, String.format(description, args));
    }

    public static ErrorResult forbidden(ErrorCode code, String description, Object... args) {
        return new ErrorResult(ErrorType.FORBIDDEN, code, String.format(description, args));
    }

    public static ErrorResult notFound(ErrorCode code, String description, Object... args) {
        return new ErrorResult(ErrorType.NOT_FOUND, code, String.format(description, args));
    }

    public static ErrorResult internalError(ErrorCode code, String description, Object... args) {
        return new ErrorResult(ErrorType.INTERNAL_ERROR, code, String.format(description, args));
    }

    public static ErrorResult serviceUnavailable(ErrorCode code, String description, Object... args) {
        return new ErrorResult(ErrorType.SERVICE_UNAVAILABLE, code, String.format(description, args));
    }

    //TODO: test
    public ErrorException toException() {
        return new ErrorException(this);
    }
}
