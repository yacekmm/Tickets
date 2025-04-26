package com.bottega.payment.infra.consumerredelivery.consumer;

import com.bottega.payment.infra.consumerredelivery.consumer.SmtpClient.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Iterator;

@RequiredArgsConstructor
public class InboxProcessor {

    private final InboxRepo inboxRepo;
    private final SmtpClient smtpClient;
    private final RedeliveryHttpClient redeliveryHttpClient;

    @Scheduled
    public void processInbox() {
        Iterator<EmailSeqReq> iterator = inboxRepo.findAll().iterator();

        EmailSeqReq recentItem = inboxRepo.findLastProcessed();

        while(iterator.hasNext()){
            EmailSeqReq item = iterator.next();
            if (item.isNextAfter(recentItem)) {
                smtpClient.send(Mail.from(item));
                inboxRepo.markProcessed(item);
                recentItem = item;
            } else {
                requestMissingItemsBetween(recentItem, item);
            }
        }
    }

    private void requestMissingItemsBetween(EmailSeqReq from, EmailSeqReq to) {
        redeliveryHttpClient.requestItemsBetween(from.sequence(), to.sequence());
    }

}
