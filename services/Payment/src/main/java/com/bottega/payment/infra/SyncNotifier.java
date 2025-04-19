package com.bottega.payment.infra;

import com.bottega.payment.domain.Payment;
import com.bottega.payment.domain.ports.out.Notifier;

public class SyncNotifier implements Notifier {

    @Override
    public void sendConfirmation(Payment payment) {
        //call REST API
    }
}
