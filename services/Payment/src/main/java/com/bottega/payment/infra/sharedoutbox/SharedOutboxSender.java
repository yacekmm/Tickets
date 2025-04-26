package com.bottega.payment.infra.sharedoutbox;

import com.bottega.payment.domain.Payment;
import com.bottega.payment.domain.ports.out.Notifier;
import com.bottega.payment.infra.HttpClientReq;
import com.bottega.payment.infra.NotifierHttpClient;
import com.bottega.payment.infra.outbox.NotifierOutboxRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SharedOutboxSender implements Notifier {

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
