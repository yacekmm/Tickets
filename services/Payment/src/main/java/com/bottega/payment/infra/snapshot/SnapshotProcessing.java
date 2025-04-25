package com.bottega.payment.infra.snapshot;

import com.bottega.payment.domain.Payment;
import com.bottega.payment.domain.ports.out.Notifier;
import com.bottega.payment.infra.NotifierHttpClient;
import com.bottega.payment.infra.NotifierHttpClient.HttpClientReq;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SnapshotProcessing implements Notifier {

    private final SyncedPaymentRepo syncedPaymentRepo;
    private final NotifierHttpClient notifierHttpClient;

    @Override
    public void sendConfirmation(Payment payment) {

        syncedPaymentRepo.findAllDirty().forEach(dirtyPayment -> {
                notifierHttpClient.send(HttpClientReq.from(dirtyPayment));
                dirtyPayment.dirty(false);
                syncedPaymentRepo.save(dirtyPayment);
            }
        );
    }
}
