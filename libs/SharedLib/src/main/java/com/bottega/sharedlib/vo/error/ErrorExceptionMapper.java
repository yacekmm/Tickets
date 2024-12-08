package com.bottega.sharedlib.vo.error;

import com.bottega.sharedlib.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@ControllerAdvice
@Slf4j
public class ErrorExceptionMapper {

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorDto> convertToResponse(ErrorException exception) {

        ErrorResult error = exception.getError();
        log.info("Mapping Error to HTTP code: {}, error: {}", error.getType().getHttpStatus(), error);
        return ResponseEntity
                .status(error.getType().getHttpStatus())
                .contentType(APPLICATION_JSON)
                .body(ErrorDto.from(error));
    }

}