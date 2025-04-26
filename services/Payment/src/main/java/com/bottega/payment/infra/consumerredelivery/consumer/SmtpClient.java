package com.bottega.payment.infra.consumerredelivery.consumer;

public class SmtpClient {

    public void send(Mail mail) {
        //send email
    }

    public record Mail(){
        public static Mail from(EmailSeqReq emailSeqReq) {
            return new Mail();
        }
    }
}
