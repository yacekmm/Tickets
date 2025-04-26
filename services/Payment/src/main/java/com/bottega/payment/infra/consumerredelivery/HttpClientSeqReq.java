package com.bottega.payment.infra.consumerredelivery;

import com.bottega.payment.infra.HttpClientReq;

public record HttpClientSeqReq (
        HttpClientReq httpClientReq,
        long sequence
) {
    public static HttpClientSeqReq from(SequencedPayment payment) {
        return new HttpClientSeqReq(
                HttpClientReq.from(payment),
                payment.getSequenceNumber()
        );
    }
}
