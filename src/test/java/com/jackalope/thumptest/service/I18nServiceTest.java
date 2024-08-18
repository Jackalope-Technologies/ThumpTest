/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class I18nServiceTest {
    private I18nService i18nService;

    @BeforeEach
    void setup() {
        i18nService = new I18nService();
    }

    @Test
    void testGetBundle() {
        ResourceBundle actualResourceBundle = i18nService.getBundle();

        assertNotNull(actualResourceBundle);
    }

    @Test
    void testGetString() {
        var expected = "Clear";

        var actual = i18nService.getString("button.clear");

        assertEquals(expected, actual);
    }

    @Test
    void testGetFormattedString() {
        var expected = "Performing CPU bench test for 50 runs..";

        Object[] performMessageArgs = {
                50
        };

        var actual = i18nService.getFormattedString("text.performCPUBenchTest", performMessageArgs);

        assertEquals(expected, actual);
    }

}