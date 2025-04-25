package com.bottega.payment.domain.ports.out;


import com.bottega.payment.domain.Payment;
import com.bottega.payment.domain.PaymentId;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepo extends CrudRepository<Payment, PaymentId> {
    Iterable<Payment> findAllDirty();
}
