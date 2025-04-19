package com.bottega.payment.domain.ports.out;

import com.bottega.payment.domain.Payment;

public interface Notifier {
    void sendConfirmation(Payment payment);
}
