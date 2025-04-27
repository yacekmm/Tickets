package com.bottega.payment.infra.compensation;

import com.bottega.payment.infra.consumerredelivery.consumer.EmailSeqReq;
import com.bottega.payment.infra.consumerredelivery.consumer.InboxRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/consumer")
@RequiredArgsConstructor
public class CompensatingController {

    private final InboxRepo inboxRepo;

    @PostMapping("/send-email")
    public ResponseEntity<Void> sendEmail(@RequestBody EmailSeqReq emailSeqReq) {
        inboxRepo.save(emailSeqReq);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/send-email")
    public ResponseEntity<Void> cancelSendEmail(@RequestBody UUID eventId) {
        inboxRepo.findById(eventId).ifPresentOrElse(
                inboxRepo::delete,
                () -> {throw new IllegalArgumentException("Too Late to cancel");});

        return ResponseEntity.ok().build();
    }
}