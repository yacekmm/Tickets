package com.bottega.pricing.fixtures;

import com.bottega.sharedlib.config.ServicesProperties;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public class CdcFrameworkTestBase extends FrameworkTestBase {

    @Autowired
    ServicesProperties servicesProperties;

    @BeforeEach
    void setUp() {
    }
}
