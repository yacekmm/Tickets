package com.bottega.vendor.contract;

import com.bottega.vendor.shared.AggregateId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Embeddable
public class VendorId implements AggregateId {

    @Getter
    @Column(updatable = false, nullable = false, name = "id")
    protected String value;

    public static VendorId generate() {
        return new VendorId(UUID.randomUUID().toString());
    }

    public String asString() {
        return value;
    }
}
