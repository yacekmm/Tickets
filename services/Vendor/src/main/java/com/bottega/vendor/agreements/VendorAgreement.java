package com.bottega.vendor.agreements;

import lombok.Builder;

@Builder
public record VendorAgreement(
        VendorId vendorId,
        int profitMarginPercentage
) {
}
