package com.bottega.sharedlib.fixtures;

import java.util.*;

import com.bottega.sharedlib.event.*;
import com.bottega.sharedlib.vo.error.ErrorResult;
import io.vavr.control.Either;
import static org.assertj.core.api.Assertions.assertThat;

public class FakeEventPublisher implements EventPublisher {

    private List<Event> events = new ArrayList<>();

    @Override
    public Either<ErrorResult, String> publish(Event event) {
        events.add(event);
        return Either.right(null);
    }

    public Event singleEvent() {
        assertThat(events).hasSize(1);
        return events.getFirst();
    }
}
