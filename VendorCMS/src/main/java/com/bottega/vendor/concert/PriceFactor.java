package com.bottega.vendor.concert;

import java.util.Map;

public record PriceFactor (
        String name,
        Map<String, String> params
){
}
