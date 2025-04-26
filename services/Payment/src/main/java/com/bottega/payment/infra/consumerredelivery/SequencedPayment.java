package com.bottega.payment.infra.consumerredelivery;

import com.bottega.payment.domain.Payment;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;

import static jakarta.persistence.GenerationType.SEQUENCE;

@SequenceGenerator(name = "seq_gen", sequenceName = "payment_sequence", allocationSize = 1)
public class SequencedPayment extends Payment {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_gen")
    @Getter
    private Long sequenceNumber;
}
