package com.bottega.payment.fixtures;

import com.bottega.payment.domain.Money;
import com.bottega.payment.domain.PayerEmail;
import com.bottega.payment.domain.Payment;
import com.bottega.payment.domain.ports.out.PaymentRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PaymentBuilder {

    private final PaymentRepo paymentRepo;

    public Payment build() {
        return new Payment(new Money(100.00), PayerEmail.from("payer@mail.com"));
    }

    public Payment inDb() {
        return paymentRepo.save(build());
    }
}
