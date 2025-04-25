package com.bottega.payment.infra;

import com.bottega.payment.domain.Payment;
import org.springframework.web.reactive.function.client.WebClient;

public class NotifierHttpClient {

    private WebClient notifierClient;

    public void send(HttpClientReq req) {
        notifierClient.post()
                .uri("/v1/email")
                .bodyValue(req)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

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
}
