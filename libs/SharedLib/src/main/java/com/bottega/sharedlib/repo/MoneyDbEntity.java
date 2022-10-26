package com.bottega.sharedlib.repo;

import com.bottega.sharedlib.vo.Money;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

import static lombok.AccessLevel.PRIVATE;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class MoneyDbEntity {

    private int money;

    public static MoneyDbEntity from(Money money){
        return new MoneyDbEntity(money.toInt());
    }

    public static Money toMoney(MoneyDbEntity money){
        return new Money(money.money);
    }
}
