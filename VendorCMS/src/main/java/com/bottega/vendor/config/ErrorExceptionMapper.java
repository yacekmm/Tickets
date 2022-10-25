package com.bottega.vendor.config;

import com.bottega.sharedlib.dto.ErrorDto;
import com.bottega.sharedlib.vo.error.ErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorExceptionMapper {

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorDto> convertToResponse(ErrorException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorDto.from(exception.getError()));
    }

}