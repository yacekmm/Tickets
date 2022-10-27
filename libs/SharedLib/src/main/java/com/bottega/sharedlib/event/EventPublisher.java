package com.bottega.sharedlib.event;

import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;

public interface EventPublisher {
    Either<ErrorResult, String> publish(Event event);
}
