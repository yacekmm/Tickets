package com.bottega.payment.infra.naiveasync;

import com.bottega.payment.domain.Payment;
import com.bottega.payment.domain.ports.out.Notifier;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public class NaiveAsyncNotifier implements Notifier {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void sendConfirmation(Payment payment) {
        kafkaTemplate.send("payment", payment);
    }
}
