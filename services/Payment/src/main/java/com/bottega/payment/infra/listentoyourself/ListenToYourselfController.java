package com.bottega.payment.infra.listentoyourself;

import com.bottega.payment.infra.consumerredelivery.consumer.EmailSeqReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consumer")
@RequiredArgsConstructor
public class ListenToYourselfController {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("/send-email")
    public ResponseEntity<Void> sendEmail(@RequestBody EmailSeqReq emailSeqReq) {
        kafkaTemplate.send("email.send", emailSeqReq);
        return ResponseEntity.ok().build();
    }
}