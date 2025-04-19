package com.bottega.payment.infra.outbox;

import com.bottega.sharedlib.repo.AggregateId;

public class PaymentConfirmationId  extends AggregateId {

    public PaymentConfirmationId() {
        super(AggregateId.generate());
    }

    public PaymentConfirmationId(String id) {
        super(id);
    }
}
