package com.bottega.pricing.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    public NewTopic concert(){
        return TopicBuilder.name("vendor.concert").partitions(1).build();
    }

    @Bean
    public NewTopic price(){
        return TopicBuilder.name("pricing.price").partitions(1).build();
    }
}
