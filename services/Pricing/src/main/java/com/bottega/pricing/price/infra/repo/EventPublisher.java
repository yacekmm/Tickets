package com.bottega.pricing.price.infra.repo;

import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.sharedlib.vo.event.Event;
import io.vavr.control.Either;

public interface EventPublisher {
    Either<ErrorResult, String> publish(Event event);
}
