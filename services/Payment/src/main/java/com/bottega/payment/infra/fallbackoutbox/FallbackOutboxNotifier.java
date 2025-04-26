package com.bottega.payment.infra.fallbackoutbox;

import com.bottega.payment.domain.Payment;
import com.bottega.payment.domain.ports.out.Notifier;
import com.bottega.payment.infra.NotifierHttpClient;
import com.bottega.payment.infra.outbox.NotifierOutboxRepo;
import com.bottega.payment.infra.outbox.PaymentConfirmation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FallbackOutboxNotifier implements Notifier {

    private final NotifierOutboxRepo notifierOutboxRepo;
    private final NotifierHttpClient notifierHttpClient;

    @Override
    public void sendConfirmation(Payment payment) {
        try {
            notifierHttpClient.send(NotifierHttpClient.HttpClientReq.from(payment));
        } catch (Exception e) {
            System.err.println("Failed to send notification, saving to outbox: " + e.getMessage());
            notifierOutboxRepo.save(PaymentConfirmation.from(payment));
        }
    }
}
