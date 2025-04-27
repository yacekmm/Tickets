package com.bottega.payment.infra.consumerredelivery.consumer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface InboxRepo extends CrudRepository<EmailSeqReq, UUID> {
    EmailSeqReq findLastProcessed();

    void markProcessed(EmailSeqReq item);

    EmailSeqReq findEmailSeqReqBy(long sequence);

    List<EmailSeqReq> findAllOrderBySequence();
}
