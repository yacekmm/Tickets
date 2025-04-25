package com.bottega.payment.infra.sharedoutbox;

import com.bottega.payment.domain.Payment;
import com.bottega.payment.domain.ports.out.Notifier;
import com.bottega.payment.infra.outbox.NotifierOutboxRepo;
import com.bottega.payment.infra.outbox.PaymentConfirmation;

public class SharedOutboxNotifier implements Notifier {

    private NotifierOutboxRepo notifierOutboxRepo;

    @Override
    public void sendConfirmation(Payment payment) {
        notifierOutboxRepo.save(PaymentConfirmation.from(payment));
    }
}
