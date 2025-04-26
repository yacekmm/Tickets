package com.bottega.payment.infra.consumerredelivery.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consumer")
@RequiredArgsConstructor
public class ConsumerController {

    private final InboxRepo inboxRepo;

    @PostMapping("/send-email")
    public ResponseEntity<Void> sendEmail(@RequestBody EmailSeqReq emailSeqReq) {
        inboxRepo.save(emailSeqReq);
        return ResponseEntity.ok().build();
    }
}