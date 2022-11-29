package com.bottega.sharedlib.vo.error

import spock.lang.Specification

import static com.bottega.sharedlib.vo.error.ErrorType.*
import static com.bottega.sharedlib.vo.error.GenericErrorCode.invalid_request

class ErrorResult_MicroSpec extends Specification {

    private static String desc = "my description"
    private static ErrorCode errCode = invalid_request

    def "toException - returns exception from error result"() {
        expect:
        with(errorResult.toException().getError()) {
            type == expectedErrorType
            code == errCode
            description == desc
        }

        where:
        errorResult                                   | expectedErrorType
        ErrorResult.badRequest(errCode, desc)         | BAD_REQUEST
        ErrorResult.unauthorized(errCode, desc)       | UNAUTHORIZED
        ErrorResult.forbidden(errCode, desc)          | FORBIDDEN
        ErrorResult.notFound(errCode, desc)           | NOT_FOUND
        ErrorResult.internalError(errCode, desc)      | INTERNAL_ERROR
        ErrorResult.serviceUnavailable(errCode, desc) | SERVICE_UNAVAILABLE
    }

}
