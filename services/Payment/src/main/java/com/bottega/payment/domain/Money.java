package com.bottega.payment.domain;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Embeddable
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Money {
    private double amount;

    public Money(double amount) {
        this.amount = roundToTwoDecimalPlaces(amount);
    }

    private double roundToTwoDecimalPlaces(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public Money add(Money money) {
        return new Money(amount + money.amount);
    }

    public Money discount(int percentage) {
        return new Money(amount * (100 - percentage) / 100);
    }
}