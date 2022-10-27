package com.bottega.pricing.price.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "serialization_type")
@JsonSubTypes({@JsonSubTypes.Type(value = PercentageFactor.class, name = "PERCENTAGE")})
public interface FactorXXX {
    int getValue();

    FactorType getType();
}
