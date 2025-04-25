package com.bottega.payment.infra.multioutbox;

import com.bottega.payment.domain.Payment;
import com.bottega.payment.domain.ports.out.Notifier;
import com.bottega.payment.infra.outbox.NotifierOutboxRepo;
import com.bottega.payment.infra.outbox.PaymentConfirmation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MultiOutboxNotifier implements Notifier {

    private final NotifierOutboxRepo emailNotifierOutboxRepo;
    private final NotifierOutboxRepo smsNotifierOutboxRepo;

    @Override
    public void sendConfirmation(Payment payment) {
        emailNotifierOutboxRepo.save(PaymentConfirmation.from(payment));
        smsNotifierOutboxRepo.save(PaymentConfirmation.from(payment));
    }
}
