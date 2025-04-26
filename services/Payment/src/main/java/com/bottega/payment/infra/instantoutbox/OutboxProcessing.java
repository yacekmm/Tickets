package com.bottega.payment.infra.instantoutbox;

import com.bottega.payment.infra.HttpClientReq;
import com.bottega.payment.infra.NotifierHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
public class OutboxProcessing {

    private final NotifierOutboxRepo notifierOutboxRepo;
    private final NotifierHttpClient notifierHttpClient;

    @Scheduled
    public void processOutbox() {
        notifierOutboxRepo.findAll().forEach(confirmation -> {
                notifierHttpClient.send(HttpClientReq.from(confirmation.getPayment()));
                notifierOutboxRepo.delete(confirmation);
            }
        );
    }
}
