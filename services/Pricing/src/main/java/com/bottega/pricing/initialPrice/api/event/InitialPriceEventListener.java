package com.bottega.pricing.initialPrice.api.event;

import com.bottega.pricing.initialPrice.InitialPriceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
@Configuration
public class InitialPriceEventListener {

    private final InitialPriceService initialPriceService;

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("vendor.concert")
                .partitions(10)
                .replicas(1)
                .build();
    }

    //TODO: rename group to tickets
    @KafkaListener(id="my-id", topics = "vendor.concert")
    public void listen(String message) {
//        Event event = message.getPayload();
        log.info("ddddddd Received: " + message);
//        initialPriceService.
//        if (event.getFoo().startsWith("fail")) {
//            throw new RuntimeException("failed");
//        }
//        this.storedFoo = event;
//        this.storedFooMessage = message;
    }
}
