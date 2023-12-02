package com.bottega.promoter.config;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import static com.bottega.sharedlib.config.ApiVersions.V1;
import static com.bottega.sharedlib.vo.error.ErrorResult.badRequest;
import static com.bottega.sharedlib.vo.error.GenericErrorCode.illegal_argument;

@RestController
@AllArgsConstructor
public class ExceptionThrowingController {

    @GetMapping(path = V1 + "/exception")
    @ResponseBody
    public void getError() {

        throw badRequest(illegal_argument, "make it a nice json, please")
                .toException();
    }

}
