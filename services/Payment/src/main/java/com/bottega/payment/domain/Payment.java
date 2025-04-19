package com.bottega.payment.domain;


import com.bottega.sharedlib.repo.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payments")
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Payment implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private PaymentId id;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Embedded
    private Money amount;

    @Embedded
    private PayerEmail payerEmail;


    public Payment(Money amount, PayerEmail payerEmail) {
        this.id = new PaymentId();
        this.status = PaymentStatus.CREATED;
        this.amount = amount;
        this.payerEmail = payerEmail;
    }


    public void paid() {
        this.status = PaymentStatus.PAID;
    }

}