package com.bottega.promoter.concert;

import java.util.Map;

public record PriceFactor (
        String type,
        int value,
        Map<String, String> params
){
}
