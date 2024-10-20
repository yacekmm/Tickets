package com.bottega.promoter.concertRead;

import com.bottega.promoter.concert.domain.ConcertId;
import com.bottega.sharedlib.event.Event;
import com.bottega.sharedlib.event.payload.PriceChangeEventPayload;
import com.bottega.sharedlib.vo.Money;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class PriceChangedEventListener {

    private final ConcertReadService concertReadService;
    private final ObjectMapper objectMapper;


    @KafkaListener(id = "price-listener", topics = "pricing.price")
    public void listen(Message<String> message) {

        Try.of(() -> objectMapper.readValue(message.getPayload(), Event.class))
                .onFailure(throwable -> log.info("Error Parsing kafka message: {}, ex: {}", message, throwable))
                .peek(this::broker);
    }

    public void broker(Event event) {
        if (event.getPayload() instanceof PriceChangeEventPayload payload) {
            concertReadService.updatePrice(new ConcertId(payload.itemId()), new Money(payload.price()));
        } else {
            log.info("Ignoring event: unsupported payload type: {}", event.getPayload());
        }
    }
}
