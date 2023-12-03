package com.bottega.promoter.agreements;

import lombok.Builder;

@Builder
public record PromoterAgreement(
        PromoterId promoterId,
        int profitMarginPercentage
) {
}
