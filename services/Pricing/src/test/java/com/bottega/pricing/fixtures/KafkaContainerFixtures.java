package com.bottega.pricing.fixtures;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.*;

import org.apache.commons.logging.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.verifier.converter.YamlContract;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifierReceiver;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.context.annotation.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.*;
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
class TestConfig {

    @Bean
    KafkaMessageVerifier kafkaTemplateMessageVerifier() {
        return new KafkaMessageVerifier();
    }

}


class KafkaMessageVerifier implements MessageVerifierReceiver<Message<?>> {

    private static final Log LOG = LogFactory.getLog(KafkaMessageVerifier.class);

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
            LOG.info("Removed a message from a topic [" + destination + "]: " + message);
        }
        return message;
    }


    @KafkaListener(id = "contractTestListener", topicPattern = ".*")
    public void listen(ConsumerRecord payload, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        LOG.info("Got a message from a topic [" + topic + "]: " + payload);
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
