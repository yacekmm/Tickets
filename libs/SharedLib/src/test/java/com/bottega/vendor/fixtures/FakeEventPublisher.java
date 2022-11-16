package com.bottega.vendor.fixtures;

import com.bottega.sharedlib.event.*;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;

public class FakeEventPublisher implements EventPublisher {

    @Override
    public Either<ErrorResult, String> publish(Event event) {
        return Either.right(null);
    }

}
