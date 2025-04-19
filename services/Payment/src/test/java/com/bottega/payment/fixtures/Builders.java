package com.bottega.payment.fixtures;

import com.bottega.payment.domain.ports.out.PaymentRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Builders {

    public final PaymentRepo paymentRepo;

    public PaymentBuilder aPayment() {
        return new PaymentBuilder(paymentRepo);
    }


}
