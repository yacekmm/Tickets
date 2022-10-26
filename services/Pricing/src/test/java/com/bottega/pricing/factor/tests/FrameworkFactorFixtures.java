package com.bottega.pricing.factor.tests;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FrameworkFactorFixtures {


    public final FactorApiClient factorClient;

    public void tearDown() {

    }

}
