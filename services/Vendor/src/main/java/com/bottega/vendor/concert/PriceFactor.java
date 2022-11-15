package com.bottega.vendor.concert;

import java.util.Map;

public record PriceFactor (
        String name,
        int value,
        Map<String, String> params
){
}
