package com.bottega.payment.domain;

import com.bottega.sharedlib.repo.AggregateId;
import jakarta.persistence.Embeddable;


@Embeddable
public class PaymentId extends AggregateId {

    public PaymentId() {
        super(AggregateId.generate());
    }

    public PaymentId(String id) {
        super(id);
    }
}
