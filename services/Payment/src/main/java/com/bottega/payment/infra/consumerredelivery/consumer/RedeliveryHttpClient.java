package com.bottega.payment.infra.consumerredelivery.consumer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedeliveryHttpClient {

    public void requestItemsBetween(long from, long to) {
        log.info("Requesting items between {} and {}", from, to);
    }
}
