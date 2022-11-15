package com.bottega.sharedlib.config;

import org.springframework.context.annotation.*;

import java.time.*;

import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoUnit.DAYS;

@Configuration
public class TestClockConfig {

    public static final Instant TEST_TIME = Instant.parse("2022-02-05T07:20:00Z");
    public static final Instant TEST_TIME_PLUS_30_DAYS = TEST_TIME.plus(30, DAYS);
    public static final Instant TEST_TIME_PLUS_60_DAYS = TEST_TIME.plus(60, DAYS);

    @Primary
    @Bean
    public Clock testClock() {
        return Clock.fixed(TEST_TIME, UTC);
    }

}
