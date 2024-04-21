package com.bottega.pricing.fixtures;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.*;

import lombok.AllArgsConstructor;
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
import org.springframework.messaging.*;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@AutoConfigureMessageVerifier
public class KafkaContainerFixtures {


    @Autowired
    private KafkaReceiverVerifier kafkaReceiverVerifier;

    void beforeEach() {
        kafkaReceiverVerifier.reset();
    }
}

@Configuration
@Slf4j
class TestConfig {

    @Bean
    KafkaReceiverVerifier messageReceiverVerifier() {
        return new KafkaReceiverVerifier();
    }

    @Bean
    @SuppressWarnings({"rawtypes"})
    KafkaSenderVerifier messageSenderVerifier(KafkaTemplate kafkaTemplate) {
        return new KafkaSenderVerifier(kafkaTemplate);
    }
}

@Slf4j
@AllArgsConstructor
@SuppressWarnings({"rawtypes", "unchecked"})
class KafkaSenderVerifier implements MessageVerifierSender {

    private KafkaTemplate kafkaTemplate;

    @Override
    public void send(Object payload, Map headers, String destination, @org.jetbrains.annotations.Nullable YamlContract contract) {
        Map<String, Object> newHeaders = headers != null ? new HashMap<>(headers) : new HashMap<>();
        newHeaders.put(KafkaHeaders.TOPIC, destination);
        log.info("Sending: " + payload + destination);
        kafkaTemplate.send(MessageBuilder.createMessage(payload, new MessageHeaders(newHeaders)));
    }

    @Override
    public void send(Object message, String destination, @org.jetbrains.annotations.Nullable YamlContract contract) {

    }
}

@Slf4j
@SuppressWarnings({"rawtypes"})
class KafkaReceiverVerifier implements MessageVerifierReceiver<Message<?>> {

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
