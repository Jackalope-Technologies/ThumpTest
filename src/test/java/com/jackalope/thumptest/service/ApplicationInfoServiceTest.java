/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationInfoServiceTest {
    private ApplicationInfoService applicationInfoService;

    @BeforeEach
    void setup() {
        applicationInfoService = new ApplicationInfoService();
    }

    @Test
    void getHomepageReturnsExpected() {
        var expectedValue = "https://github.com/Jackalope-Technologies/ThumpTest";

        String actualValue = applicationInfoService.getHomepage();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getVersionReturnsExpected() {
        var expectedValue = "0.0.1";

        String actualValue = applicationInfoService.getVersion();

        assertEquals(expectedValue, actualValue);
    }
}