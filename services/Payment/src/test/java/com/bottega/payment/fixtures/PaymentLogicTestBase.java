package com.bottega.payment.fixtures;

public class PaymentLogicTestBase {

    public PaymentFixturesFactory paymentFixturesFactory = new PaymentFixturesFactory();
    public Builders builders = new Builders(paymentFixturesFactory.paymentRepo);
}
