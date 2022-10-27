package com.bottega.pricing.fixtures;

import com.bottega.pricing.price.infra.repo.EventPublisher;
import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.sharedlib.vo.event.Event;
import io.vavr.control.Either;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class FakeEventPublisher implements EventPublisher {

    private final List<Event> events = new ArrayList<>();
    @Override
    public Either<ErrorResult, String> publish(Event event) {
        events.add(event);
        return Either.right(null);
    }

    public Event singleEvent() {
        Assertions.assertThat(events).hasSize(1);
        return events.get(0);
    }
}
