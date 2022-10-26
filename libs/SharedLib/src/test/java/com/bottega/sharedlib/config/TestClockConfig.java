package com.bottega.sharedlib.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Clock;
import java.time.Instant;

import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoUnit.DAYS;

@Configuration
public class TestClockConfig {

    public static final Instant TEST_TIME = Instant.parse("2022-02-05T07:20:00Z");
    public static final Instant TEST_TIME_PLUS_30_DAYS = TEST_TIME.plus(30, DAYS);

    @Primary
    @Bean
    public Clock testClock() {
        return Clock.fixed(TEST_TIME, UTC);
    }

}
