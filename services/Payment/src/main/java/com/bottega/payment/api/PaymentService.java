package com.bottega.payment.api;

import com.bottega.payment.domain.Payment;
import com.bottega.payment.domain.PaymentId;
import com.bottega.payment.domain.ports.out.Notifier;
import com.bottega.payment.domain.ports.out.PaymentRepo;
import com.bottega.sharedlib.ddd.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@ApplicationService
public class PaymentService {

    private final PaymentRepo paymentRepo;
    private final Notifier notifier;

    @Transactional
    public void onPaid(PaymentId paymentId) {
        Payment payment = paymentRepo.findById(paymentId).orElseThrow();
        payment.paid();

        notifier.sendConfirmation(payment);
        paymentRepo.save(payment);
    }
}
