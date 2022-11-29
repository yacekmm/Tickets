package com.bottega.sharedlib.vo.error;

import lombok.*;

import static com.bottega.sharedlib.vo.error.ErrorType.*;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@Getter
@ToString
@AllArgsConstructor(access = PRIVATE)
@EqualsAndHashCode
public class ErrorResult {

    private final ErrorType type;
    private final ErrorCode code;
    private final String description;

    public static ErrorResult badRequest(ErrorCode code, String description, Object... args) {
        return new ErrorResult(BAD_REQUEST, code, format(description, args));
    }

    public static ErrorResult unauthorized(ErrorCode code, String description, Object... args) {
        return new ErrorResult(UNAUTHORIZED, code, format(description, args));
    }

    public static ErrorResult forbidden(ErrorCode code, String description, Object... args) {
        return new ErrorResult(FORBIDDEN, code, format(description, args));
    }

    public static ErrorResult notFound(ErrorCode code, String description, Object... args) {
        return new ErrorResult(NOT_FOUND, code, format(description, args));
    }

    public static ErrorResult internalError(ErrorCode code, String description, Object... args) {
        return new ErrorResult(INTERNAL_ERROR, code, format(description, args));
    }

    public static ErrorResult serviceUnavailable(ErrorCode code, String description, Object... args) {
        return new ErrorResult(SERVICE_UNAVAILABLE, code, format(description, args));
    }

    public ErrorException toException() {
        return new ErrorException(this);
    }
}
