package com.bottega.sharedlib.repo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor
@AllArgsConstructor(access = PROTECTED)
@MappedSuperclass
@EqualsAndHashCode
@ToString
public class AggregateId implements Serializable {

    @Getter
    @Column(insertable = false, updatable = false, name = "id")
    protected String value;

    public AggregateId(AggregateId aggId) {
        this.value = aggId.value;
    }

    public static AggregateId generate() {
        return new AggregateId(UUID.randomUUID().toString());
    }

    public String asString() {
        return value;
    }
}
