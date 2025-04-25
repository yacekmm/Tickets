package com.bottega.payment.infra.snapshot;

import com.bottega.payment.domain.Payment;
import com.bottega.payment.domain.ports.out.PaymentRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SyncedPaymentRepo {

    private final PaymentRepo paymentRepo;

    public Payment saveDirty(Payment entity) {
        entity.dirty(true);
        return paymentRepo.save(entity);
    }

    public void save(Payment payment) {
        paymentRepo.save(payment);
    }

    public Iterable<Payment> findAllDirty() {
        return paymentRepo.findAllDirty();
    }
}