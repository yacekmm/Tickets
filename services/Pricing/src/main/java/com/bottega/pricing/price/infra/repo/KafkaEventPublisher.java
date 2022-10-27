package com.bottega.pricing.price.infra.repo;

import com.bottega.pricing.price.api.app.FactorErrorCode;
import com.bottega.sharedlib.vo.error.ErrorResult;
import com.bottega.sharedlib.vo.event.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Slf4j
@Component
public class KafkaEventPublisher implements EventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;


    @Override
    public Either<ErrorResult, String> publish(Event event) {
        return Try.of(() -> objectMapper.writeValueAsString(event))
                .toEither(() -> ErrorResult.badRequest(FactorErrorCode.illegal_argument, "failed to serialize event: " + event))
                .peek(eventString -> log.info("sending to topic='{}', payload='{}' ", event.getType().getKafkaTopic(), eventString))
                .peek(eventString -> kafkaTemplate.send(event.getType().getKafkaTopic(), eventString));
    }
}
