package com.bottega.sharedlib.dto;

import com.bottega.sharedlib.vo.error.ErrorResult;


public record ErrorDto(
        String type,
        String code,
        String description) {

    public static ErrorDto from(ErrorResult errorResult){
        return new ErrorDto(
                errorResult.getType().name(),
                errorResult.getCode().toString(),
                errorResult.getDescription()
        );
    }
}
