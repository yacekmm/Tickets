package com.bottega.payment.domain;

import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
public class TransactionWrapper {

    public void executeAfterTransaction(Runnable afterTransaction) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization(){
            @Override
            public void afterCommit() {
                afterTransaction.run();
            }
        });
    }
} 