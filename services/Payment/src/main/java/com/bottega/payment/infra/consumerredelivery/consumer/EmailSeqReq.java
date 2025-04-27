package com.bottega.payment.infra.consumerredelivery.consumer;

import java.util.UUID;

public record EmailSeqReq(
        UUID id,
        String to,
        String template,
        long sequence
) {
    public boolean isNextAfter(EmailSeqReq recentItem) {
        return recentItem.sequence() + 1 == this.sequence();
    }
}
