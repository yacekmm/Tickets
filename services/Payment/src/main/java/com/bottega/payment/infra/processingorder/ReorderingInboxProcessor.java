package com.bottega.payment.infra.processingorder;

import com.bottega.payment.infra.consumerredelivery.consumer.InboxRepo;
import com.bottega.payment.infra.consumerredelivery.consumer.SmtpClient;
import com.bottega.payment.infra.consumerredelivery.consumer.SmtpClient.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
public class ReorderingInboxProcessor {

    private final InboxRepo inboxRepo;
    private final SmtpClient smtpClient;

    @Scheduled
    public void processInbox() {
        inboxRepo.findAllOrderBySequence()
                .forEach(item -> smtpClient.send(Mail.from(item)));
    }
}
