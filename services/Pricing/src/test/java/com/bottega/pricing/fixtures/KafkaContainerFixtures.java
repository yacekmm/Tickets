package com.bottega.pricing.fixtures;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.*;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.verifier.converter.YamlContract;
import org.springframework.cloud.contract.verifier.messaging.*;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.context.annotation.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.*;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.messaging.*;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@AutoConfigureMessageVerifier
public class KafkaContainerFixtures {


    @Autowired
    private KafkaMessageVerifier kafkaMessageVerifier;

    void beforeEach() {
        kafkaMessageVerifier.reset();
    }
}

@Configuration
@Slf4j
class TestConfig {

    @Bean
    KafkaMessageVerifier kafkaTemplateMessageVerifier() {
        return new KafkaMessageVerifier();
    }

    @Bean
    MessageVerifierSender<Message<?>> standaloneMessageVerifier(KafkaTemplate kafkaTemplate) {
        return new MessageVerifierSender<>() {

            @Override
            public void send(Message<?> message, String destination, @Nullable YamlContract contract) {
            }

            @Override
            public <T> void send(T payload, Map<String, Object> headers, String destination, @Nullable YamlContract contract) {
                Map<String, Object> newHeaders = headers != null ? new HashMap<>(headers) : new HashMap<>();
                newHeaders.put(KafkaHeaders.TOPIC, destination);
                log.info("Sending: " + payload + destination);
                kafkaTemplate.send(MessageBuilder.createMessage(payload, new MessageHeaders(newHeaders)));
            }
        };
    }

    @Bean
    @Primary
    JsonMessageConverter noopMessageConverter() {
        return new NoopJsonMessageConverter();
    }


}

class NoopJsonMessageConverter extends JsonMessageConverter {

    NoopJsonMessageConverter() {
    }

    @Override
    protected Object convertPayload(Message<?> message) {
        return message.getPayload();
    }
}

@Slf4j
class KafkaMessageVerifier implements MessageVerifierReceiver<Message<?>> {

    Map<String, BlockingQueue<Message<?>>> broker = new ConcurrentHashMap<>();

    public void reset() {
        broker.clear();
    }

    @Override
    public Message receive(String destination, long timeout, TimeUnit timeUnit, @Nullable YamlContract contract) {
        broker.putIfAbsent(destination, new ArrayBlockingQueue<>(1));
        Message<?> message;
        try {
            message = broker.get(destination).poll(timeout, timeUnit);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (message != null) {
            log.info("Removed a message from a topic [" + destination + "]: " + message);
        }
        return message;
    }


    @KafkaListener(id = "contractTestListener", topicPattern = ".*", groupId = "diff")
    public void listen(ConsumerRecord payload, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("Got a message from a topic [" + topic + "]: " + payload);
        Map<String, Object> headers = new HashMap<>();
        new DefaultKafkaHeaderMapper().toHeaders(payload.headers(), headers);
        broker.putIfAbsent(topic, new ArrayBlockingQueue<>(1));
        broker.get(topic).add(MessageBuilder.createMessage(payload.value(), new MessageHeaders(headers)));
    }

    @Override
    public Message receive(String destination, YamlContract contract) {
        return receive(destination, 15, TimeUnit.SECONDS, contract);
    }

}
