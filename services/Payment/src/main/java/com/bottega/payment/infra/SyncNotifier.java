package com.bottega.payment.infra;

import com.bottega.payment.domain.Payment;
import com.bottega.payment.domain.ports.out.Notifier;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

public class SyncNotifier implements Notifier {

    private final WebClient notifierClient = WebClient.create("https://example.com"); // Replace with actual base URL

    @Override
    public void sendConfirmation(Payment payment) {
        notifierClient.post()
                .uri("/v1/email")
                .bodyValue(from(payment))
                .retrieve()
                .toBodilessEntity()
                .block(); // Blocking call for synchronous behavior
    }

    private Map<String, Object> from(Payment payment) {
        return Map.of(
                "template", "payment_confirmation",
                "to", payment.getPayerEmail(),
                "paymentId", payment.getId().asString(),
                "status", payment.getStatus().name()
        );
    }
}
