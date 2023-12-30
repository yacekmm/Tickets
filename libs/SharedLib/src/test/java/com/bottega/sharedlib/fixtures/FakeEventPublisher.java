package com.bottega.sharedlib.fixtures;

import java.util.*;

import com.bottega.sharedlib.event.*;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import static org.assertj.core.api.Assertions.assertThat;

public class FakeEventPublisher implements EventPublisher {

    @Override
    public Either<ErrorResult, String> publish(Event event) {
        //TODO store events for later assertion
        return Either.right(null);
    }

    public Event singleEvent() {
        //TODO assert that there is only one event
        //TODO return it
        return null;
    }
}
