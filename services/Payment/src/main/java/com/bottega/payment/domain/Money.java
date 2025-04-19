package com.bottega.payment.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private Currency currency;

    public Money(double amount, Currency currency) {
        this.amount = roundToTwoDecimalPlaces(amount);
        this.currency = currency;
    }

    private double roundToTwoDecimalPlaces(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public Money add(Money money) {
        return new Money(amount + money.amount, currency);
    }

    public Money discount(int percentage) {
        return new Money(amount * (100 - percentage) / 100, currency);
    }
}