package com.bottega.payment.infra.outbox;

import com.bottega.payment.domain.Payment;
import com.bottega.payment.domain.ports.out.Notifier;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OutboxNotifier implements Notifier {

    private final NotifierOutboxRepo notifierOutboxRepo; // Replace with actual base URL

    @Override
    public void sendConfirmation(Payment payment) {
        notifierOutboxRepo.save(PaymentConfirmation.from(payment));
    }
}
