package com.bottega.payment.fixtures;

import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class PaymentLogicTestBase {

    public PaymentFixturesFactory paymentFixturesFactory = new PaymentFixturesFactory();
    public Builders builders = new Builders(paymentFixturesFactory.paymentRepo);

    @AfterTransaction
    public void afterTransaction() {
        // This method will be called after the transaction is closed
        // Add your post-transaction logic here
    }
}
