package com.bottega.pricing.fixtures;

import com.bottega.pricing.price.infra.repo.EventPublisher;
import com.bottega.sharedlib.vo.event.Event;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class FakeEventPublisher implements EventPublisher {

    private final List<Event> events = new ArrayList<>();
    @Override
    public void publish(Event event) {
        events.add(event);
    }

    public Event singleEvent() {
        Assertions.assertThat(events).hasSize(1);
        return events.get(0);
    }
}
