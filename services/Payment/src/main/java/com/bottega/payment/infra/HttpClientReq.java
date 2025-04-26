package com.bottega.payment.infra;

import com.bottega.payment.domain.Payment;

public record HttpClientReq(
        String template,
        String to,
        String paymentId,
        String status
) {
    public static HttpClientReq from(Payment payment) {
        return new HttpClientReq(
                "payment_confirmation",
                payment.getPayerEmail().toString(),
                payment.getId().asString(),
                payment.getStatus().name()
        );
    }
}
