package com.bottega.payment.infra.listentoyourself;

import com.bottega.payment.infra.consumerredelivery.consumer.EmailSeqReq;
import com.bottega.payment.infra.consumerredelivery.consumer.SmtpClient;
import com.bottega.payment.infra.consumerredelivery.consumer.SmtpClient.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;

@RequiredArgsConstructor
public class EmailSender {

    private final SmtpClient smtpClient;

    @KafkaListener(id = "email-listener", topics = "email.send")
    public void handleEmailSend(Message<EmailSeqReq> event) {
        smtpClient.send(Mail.from(event.getPayload()));
    }
}
