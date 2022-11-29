package com.bottega.sharedlib.dto;

import com.bottega.sharedlib.repo.AggregateId;

public record AggregateIdDto(
        String id) {

    public static AggregateIdDto fromAggregateId(AggregateId id){
        return new AggregateIdDto(id.asString());
    }
}
