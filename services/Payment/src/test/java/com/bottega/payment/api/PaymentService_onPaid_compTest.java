package com.bottega.payment.api;

import com.bottega.payment.domain.Payment;
import com.bottega.payment.fixtures.PaymentFixtures;
import com.bottega.payment.fixtures.PaymentLogicTestBase;
import org.junit.jupiter.api.Test;

class PaymentService_onPaid_compTest extends PaymentLogicTestBase {



    @Test
    public void onPaid_updatesStatus() {
        //given
        PaymentFixtures paymentFix = paymentFixturesFactory.sync();
        Payment payment = builders.aPayment().inDb();

        //when
        paymentFix.paymentService.onPaid(payment.getId());

        //then
//        REST API was called
    }

    @Test
    public void onPaid_sendsConfirmation() {

    }


}