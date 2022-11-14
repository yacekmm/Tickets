package com.bottega.sharedlib.vo.error


import spock.lang.Specification

import static com.bottega.sharedlib.vo.error.GenericErrorCode.invalid_request
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.MediaType.APPLICATION_JSON

class ErrorExceptionMapper_MicroSpec extends Specification {

    private static String desc = "my description"
    private static ErrorCode errCode = invalid_request

    def "convertToResponse - converts ErrorException to ResponseEntity"() {
        given:
        ErrorExceptionMapper mapper = new ErrorExceptionMapper();

        expect:
        with(mapper.convertToResponse(errorResult.toException())){

            statusCode == expectedHttpStatus
            headers.getContentType() == APPLICATION_JSON

            with(body){
                type() == errorResult.type.name()
                code() == errorResult.code.toString()
                description() == errorResult.description
            }
        }

        where:
        errorResult                                   | expectedHttpStatus
        ErrorResult.badRequest(errCode, desc)         | BAD_REQUEST
        ErrorResult.unauthorized(errCode, desc)       | UNAUTHORIZED
        ErrorResult.forbidden(errCode, desc)          | FORBIDDEN
        ErrorResult.notFound(errCode, desc)           | NOT_FOUND
        ErrorResult.internalError(errCode, desc)      | INTERNAL_SERVER_ERROR
        ErrorResult.serviceUnavailable(errCode, desc) | SERVICE_UNAVAILABLE
    }
}
