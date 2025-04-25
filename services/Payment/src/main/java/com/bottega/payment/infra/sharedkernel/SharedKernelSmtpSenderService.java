package com.bottega.payment.infra.sharedkernel;

import com.bottega.payment.domain.Payment;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SharedKernelSmtpSenderService {

    private final SharedKernelPaymentRepo sharedKernelPaymentRepo;
    private Payment lastPayment;
    private final SmtpClient smtpClient;

    public void sendConfirmation() {
        sharedKernelPaymentRepo.findAllAfter(lastPayment)
                .forEach(payment -> {
                    smtpClient.send(SmtpClient.Mail.from(payment));
                    lastPayment = payment;
                });
    }
}
