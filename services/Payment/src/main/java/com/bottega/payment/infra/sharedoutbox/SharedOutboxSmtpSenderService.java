package com.bottega.payment.infra.sharedoutbox;

import com.bottega.payment.infra.outbox.NotifierOutboxRepo;
import com.bottega.payment.infra.sharedkernel.SmtpClient;
import com.bottega.payment.infra.sharedkernel.SmtpClient.Mail;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SharedOutboxSmtpSenderService {

    private final NotifierOutboxRepo notifierOutboxRepo;
    private final SmtpClient smtpClient;

    public void sendConfirmation() {
        notifierOutboxRepo.findAll().forEach(confirmation -> {
            smtpClient.send(Mail.from(confirmation));
            notifierOutboxRepo.delete(confirmation);
        });
    }
}
