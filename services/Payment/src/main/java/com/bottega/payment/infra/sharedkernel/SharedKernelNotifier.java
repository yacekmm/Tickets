package com.bottega.payment.infra.sharedkernel;

import com.bottega.payment.domain.Payment;
import com.bottega.payment.domain.ports.out.Notifier;

public class SharedKernelNotifier implements Notifier {

    @Override
    public void sendConfirmation(Payment payment) {
        // nothing...
    }
}
