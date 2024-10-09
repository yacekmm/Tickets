package com.bottega.sales.domain;


import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class OrderPriority {

    @Enumerated(EnumType.STRING)
    private PriorityType type;
    private Integer level;
}
