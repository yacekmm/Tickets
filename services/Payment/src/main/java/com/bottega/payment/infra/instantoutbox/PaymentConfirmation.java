package com.bottega.payment.infra.instantoutbox;

import com.bottega.payment.domain.Payment;
import com.bottega.sharedlib.repo.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment_confirmation_outbox")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentConfirmation implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private PaymentConfirmationId id;

    @Embedded
    private Payment payment;

    public static PaymentConfirmation from(Payment payment) {
        return new PaymentConfirmation(new PaymentConfirmationId(), payment);
    }
}
