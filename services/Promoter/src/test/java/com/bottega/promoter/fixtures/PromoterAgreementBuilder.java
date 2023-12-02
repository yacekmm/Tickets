package com.bottega.promoter.fixtures;

import com.bottega.promoter.agreements.*;

public class PromoterAgreementBuilder {

    private final PromoterAgreement.PromoterAgreementBuilder builder;

    public PromoterAgreementBuilder() {
        this.builder = PromoterAgreement.builder()
                .promoterId(new PromoterId("some-promoterId"))
                .profitMarginPercentage(33);
    }

    public PromoterAgreementBuilder forPromoter(String promoterId){
        builder.promoterId(new PromoterId(promoterId));
        return this;
    }

    public PromoterAgreement build() {
        return builder.build();
    }
}
