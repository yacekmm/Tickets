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
public class Payment implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private PaymentId id;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Embedded
    private Money amount;

    public Payment(Money amount) {
        this.id = new PaymentId();
        this.status = PaymentStatus.CREATED;
        this.amount = amount;
    }


    public void paid() {
        this.status = PaymentStatus.PAID;
    }
}