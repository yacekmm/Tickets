package com.bottega.pricing.price.infra.repo;

import com.bottega.sharedlib.vo.event.Event;
import org.springframework.stereotype.Component;

@Component
public interface EventPublisher {
    void publish(Event event);
}
