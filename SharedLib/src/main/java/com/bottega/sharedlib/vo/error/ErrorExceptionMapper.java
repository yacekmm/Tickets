package com.bottega.sharedlib.vo.error;

import com.bottega.sharedlib.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@ControllerAdvice
public class ErrorExceptionMapper {

    //TODO: test it
    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorDto> convertToResponse(ErrorException exception) {

        ErrorResult error = exception.getError();

        return ResponseEntity
                .status(error.getType().getHttpStatus())
                .contentType(APPLICATION_JSON)
                .body(ErrorDto.from(error));
    }

}