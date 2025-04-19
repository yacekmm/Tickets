package com.bottega.payment.infra;

import com.bottega.payment.infra.outbox.NotifierOutboxRepo;
import com.bottega.payment.infra.outbox.PaymentConfirmation;
import com.bottega.payment.infra.outbox.PaymentConfirmationId;
import com.bottega.sharedlib.infra.repo.InMemoryRepo;

public class InMemoryNotifierOutboxRepo
        extends InMemoryRepo<PaymentConfirmation, PaymentConfirmationId>
        implements NotifierOutboxRepo {
}
