package com.bottega.vendor.shared.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import static com.bottega.vendor.shared.error.ErrorType.*;
import static lombok.AccessLevel.PRIVATE;

@Getter
@ToString
@AllArgsConstructor(access = PRIVATE)
//TODO: copy tests from ck
public class ErrorResult {

    private final ErrorType type;
    private final ErrorCode code;
    private final String description;

    public static ErrorResult badRequest(ErrorCode code, String description) {
        return new ErrorResult(BAD_REQUEST, code, description);
    }

    public static ErrorResult unauthorized(ErrorCode code, String description) {
        return new ErrorResult(UNAUTHORIZED, code, description);
    }

    public static ErrorResult forbidden(ErrorCode code, String description) {
        return new ErrorResult(FORBIDDEN, code, description);
    }

    public static ErrorResult internalError(ErrorCode code, String description) {
        return new ErrorResult(INTERNAL_ERROR, code, description);
    }

    public static ErrorResult serviceUnavailable(ErrorCode code, String description) {
        return new ErrorResult(SERVICE_UNAVAILABLE, code, description);
    }

    private static ErrorResult notFound(ErrorCode code, String description) {
        return new ErrorResult(NOT_FOUND, code, description);
    }


    //TODO: uncomment throwing exceptions for controller advice
//    public void throwHttpException() {
//        throw Match(errorResultType).of(
//                Case($(BAD_REQUEST), new BadRequestException(errorMessage)),
//                Case($(UNAUTHORIZED), new UnauthorizedWithBodyException(errorMessage.getError(), errorMessage.getErrorDescription())),
//                Case($(FORBIDDEN), new ForbiddenException(errorMessage)),
//                Case($(NOT_FOUND), new NotFoundException(errorMessage.getError(), errorMessage.getErrorDescription())),
//                Case($(INTERNAL_ERROR), new InternalServerErrorException(errorMessage)),
//                Case($(SERVICE_UNAVAILABLE), new ServiceUnavailableException(errorMessage)),
//                Case($(), new InternalServerErrorException(server_error, "Unhandled error result: " + errorResultType))
//        );
//    }
}
