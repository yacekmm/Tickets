package com.bottega.payment.infra;

import com.bottega.payment.domain.Payment;
import com.bottega.payment.domain.PaymentId;
import com.bottega.payment.domain.ports.out.PaymentRepo;
import com.bottega.sharedlib.infra.repo.InMemoryRepo;

public class InMemoryPaymentRepo
        extends InMemoryRepo<Payment, PaymentId>
        implements PaymentRepo {
}
