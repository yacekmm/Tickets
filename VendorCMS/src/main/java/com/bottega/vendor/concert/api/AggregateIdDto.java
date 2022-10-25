package com.bottega.vendor.concert.api;

import com.bottega.sharedlib.repo.AggregateId;

record AggregateIdDto(
        String id
) {
    public static AggregateIdDto fromAggregateId(AggregateId id){
        return new AggregateIdDto(id.asString());
    }
}
