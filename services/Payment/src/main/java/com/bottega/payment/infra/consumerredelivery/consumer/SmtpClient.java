package com.bottega.payment.infra.consumerredelivery.consumer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmtpClient {

    public void send(Mail mail) {
        log.info("Sending mail...");
    }

    public record Mail(){
        public static Mail from(EmailSeqReq emailSeqReq) {
            return new Mail();
        }
    }
}
