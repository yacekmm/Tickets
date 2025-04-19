package com.bottega.payment.fixtures;

import com.bottega.payment.api.PaymentService;
import com.bottega.payment.domain.ports.out.Notifier;
import com.bottega.payment.domain.ports.out.PaymentRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PaymentFixtures {

    public PaymentRepo paymentRepo;
//    public Notifier notifier;
    public PaymentService paymentService;


    public static PaymentFixtures init(PaymentRepo paymentRepo, Notifier notifier) {
        PaymentFixtures fix = new PaymentFixtures();
        fix.paymentService = new PaymentService(paymentRepo, notifier);
        return fix;
    }
}