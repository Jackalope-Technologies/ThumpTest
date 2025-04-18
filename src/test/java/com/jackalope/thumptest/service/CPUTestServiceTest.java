/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.service;

import com.jackalope.thumptest.util.testing.HardwareTester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CPUTestServiceTest {
    private CPUTestService cpuTestService;

    @BeforeEach
    void setup() {
        var i18nService = new I18nService();
        var hardwareInfoService = new HardwareInfoService(i18nService);
        cpuTestService = new CPUTestService(hardwareInfoService, i18nService);
    }

    @Test
    void testStopTests() {
        cpuTestService.stopTests();

        assertEquals(0, cpuTestService.getTestWorkers().size());
    }

    @Test
    void testGetTestWorkers() {
        List<HardwareTester> workers = cpuTestService.getTestWorkers();

        assertEquals(0, workers.size());
    }

}