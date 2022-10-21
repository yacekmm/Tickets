package com.bottega.vendor.shared;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class AggregateId implements Serializable {

    @Getter
    @Column(updatable = false, nullable = false, name = "id")
    private String value;

    public AggregateId(AggregateId aggId) {
        this.value = aggId.value;
    }

    public static AggregateId generate() {
        return new AggregateId(UUID.randomUUID().toString());
    }
}
