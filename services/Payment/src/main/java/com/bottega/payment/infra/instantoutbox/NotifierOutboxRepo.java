package com.bottega.payment.infra.instantoutbox;

import org.springframework.data.repository.CrudRepository;

public interface NotifierOutboxRepo extends CrudRepository<PaymentConfirmation, PaymentConfirmationId> {

    //save
}
