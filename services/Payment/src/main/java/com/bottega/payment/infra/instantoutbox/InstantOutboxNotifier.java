package com.bottega.payment.infra.instantoutbox;

import com.bottega.payment.domain.Payment;
import com.bottega.payment.domain.TransactionWrapper;
import com.bottega.payment.domain.ports.out.Notifier;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InstantOutboxNotifier implements Notifier {

    private final NotifierOutboxRepo notifierOutboxRepo;
    private final OutboxProcessing outboxProcessing;
    private final TransactionWrapper transactionWrapper;

    @Override
    public void sendConfirmation(Payment payment) {
        notifierOutboxRepo.save(PaymentConfirmation.from(payment));
        transactionWrapper.executeAfterTransaction(outboxProcessing::processOutbox);
    }
}
