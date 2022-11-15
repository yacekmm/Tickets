package com.bottega.sharedlib.vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Locale;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Money {

    public static final Money ZERO = new Money(0);

    private Integer value;

    public Money add(Money other) {
        return new Money(value + other.value);
    }

    public Money subtract(Money other) {
        return new Money(value - other.value);
    }

    public Money percentage(int percentage) {
        return new Money((int) Math.round(percentage * value/100.0));
    }

    public Money percentage(Double percentage) {
        return new Money((int) Math.round(percentage * value/100));
    }

    public Integer toInt() {
        return value;
    }

    @Override
    public String toString() {
        double value = Double.valueOf(this.value) / 100;
        return String.format(Locale.US, "%.2f", value);
    }
}
