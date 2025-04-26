package com.bottega.payment.infra.consumerredelivery.consumer;

public record EmailSeqReq(
        String to,
        String template,
        long sequence
) {
    public boolean isNextAfter(EmailSeqReq recentItem) {
        return recentItem.sequence() + 1 == this.sequence();
    }
}
