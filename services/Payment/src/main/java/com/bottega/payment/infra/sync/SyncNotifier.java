package com.bottega.payment.infra.sync;

import com.bottega.payment.domain.Payment;
import com.bottega.payment.domain.ports.out.Notifier;
import com.bottega.payment.infra.NotifierHttpClient;
import com.bottega.payment.infra.NotifierHttpClient.HttpClientReq;

public class SyncNotifier implements Notifier {

    private NotifierHttpClient notifierHttpClient;

    @Override
    public void sendConfirmation(Payment payment) {
        notifierHttpClient.send(HttpClientReq.from(payment));
    }
}
