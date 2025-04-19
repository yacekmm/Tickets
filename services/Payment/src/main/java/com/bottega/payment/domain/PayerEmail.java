package com.bottega.payment.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Embeddable
@AllArgsConstructor
public class PayerEmail {

    private String email;

    public static PayerEmail from(String email) {
        return new PayerEmail(email);
    }

    @Override
    public String toString() {
        return email;
    }
}