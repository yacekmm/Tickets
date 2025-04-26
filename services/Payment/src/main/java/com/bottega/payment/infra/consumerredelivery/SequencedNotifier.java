package com.bottega.payment.infra.consumerredelivery;

public interface SequencedNotifier {
    void sendConfirmation(SequencedPayment payment);
}
