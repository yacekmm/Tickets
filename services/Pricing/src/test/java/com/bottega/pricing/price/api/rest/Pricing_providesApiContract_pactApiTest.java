package com.bottega.pricing.price.api.rest;

import com.bottega.pricing.fixtures.FrameworkTestBase;
import org.springframework.beans.factory.annotation.Value;

public class Pricing_providesApiContract_pactApiTest extends FrameworkTestBase {

    @Value("${server.port}")
    int port;

}