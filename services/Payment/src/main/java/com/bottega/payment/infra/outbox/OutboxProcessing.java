package com.bottega.payment.infra.outbox;

import com.bottega.payment.domain.Payment;
import com.bottega.payment.domain.ports.out.Notifier;
import com.bottega.payment.infra.NotifierHttpClient;
import com.bottega.payment.infra.NotifierHttpClient.HttpClientReq;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OutboxProcessing implements Notifier {

    private final NotifierOutboxRepo notifierOutboxRepo;
    private final NotifierHttpClient notifierHttpClient;

    @Override
    public void sendConfirmation(Payment payment) {

        notifierOutboxRepo.findAll().forEach(confirmation -> {
                notifierHttpClient.send(HttpClientReq.from(confirmation.getPayment()));
                notifierOutboxRepo.delete(confirmation);
            }
        );
    }
}
