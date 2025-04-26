package com.bottega.payment.infra.consumerredelivery;

import com.bottega.payment.infra.HttpClientReq;
import com.bottega.payment.infra.NotifierHttpClient;

public class SyncNotifier implements SequencedNotifier {

    private NotifierHttpClient notifierHttpClient;

    @Override
    public void sendConfirmation(SequencedPayment payment) {
        notifierHttpClient.send(HttpClientReq.from(payment));
    }
}
