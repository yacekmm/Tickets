package com.bottega.promoter.agreements;

import org.springframework.stereotype.Component;

@Component
public class PromoterService {

    public PromoterAgreement getPromoterAgreement(String promoterIdString) {
        //cheating a bit - any promoterId exists, and has margin 5
        return new PromoterAgreement(new PromoterId(promoterIdString), 5);
    }
}
