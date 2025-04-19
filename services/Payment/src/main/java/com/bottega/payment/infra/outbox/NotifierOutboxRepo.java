package com.bottega.payment.infra.outbox;

import org.springframework.data.repository.CrudRepository;

public interface NotifierOutboxRepo extends CrudRepository<PaymentConfirmation, PaymentConfirmationId> {

    //save
}
