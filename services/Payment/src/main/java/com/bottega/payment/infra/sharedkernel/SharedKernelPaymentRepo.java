package com.bottega.payment.infra.sharedkernel;

import com.bottega.payment.domain.Payment;
import com.bottega.payment.domain.ports.out.PaymentRepo;

import java.util.List;

public interface SharedKernelPaymentRepo extends PaymentRepo {

    List<Payment> findAllAfter(Payment lastPayment);
}
