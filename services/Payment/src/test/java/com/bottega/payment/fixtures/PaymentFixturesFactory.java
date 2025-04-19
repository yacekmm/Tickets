package com.bottega.payment.fixtures;

import com.bottega.payment.domain.ports.out.PaymentRepo;
import com.bottega.payment.infra.InMemoryPaymentRepo;
import com.bottega.payment.infra.SyncNotifier;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PaymentFixturesFactory {

    public final PaymentRepo paymentRepo = new InMemoryPaymentRepo();

    public PaymentFixtures sync() {
        return PaymentFixtures.init(paymentRepo, new SyncNotifier());
    }

}