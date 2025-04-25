package com.bottega.payment.infra.sharedkernel;

import com.bottega.payment.domain.Payment;
import com.bottega.payment.infra.outbox.PaymentConfirmation;

public class SmtpClient {

    public void send(Mail mail) {
        //send email
    }

    public record Mail(){
        public static Mail from(Payment payment) {
            return new Mail();
        }

        public static Mail from(PaymentConfirmation confirmation) {
            return new Mail();
        }
    }
}
