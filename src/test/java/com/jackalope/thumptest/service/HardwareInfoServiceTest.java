/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HardwareInfoServiceTest {
    private HardwareInfoService hardwareInfoService;

    @BeforeEach
    void setup() {
        hardwareInfoService = new HardwareInfoService();
    }

    @Test
    void testGetCpuInfo() {
        var cpuInfo = hardwareInfoService.getCpuInfo();

        assertFalse(cpuInfo.isEmpty());
    }

    @Test
    void testGetGpuInfo() {
        var gpuInfo = hardwareInfoService.getGpuInfo();

        assertFalse(gpuInfo.isEmpty());
    }

    @Test
    void testGetCPUTemperature() {
        var cpuTemperature = hardwareInfoService.getCPUTemperature();

        assertFalse(cpuTemperature.isEmpty());
    }

    @Test
    void testGetGPUTemperature() {
        var gpuTemperature = hardwareInfoService.getCPUTemperature();

        assertFalse(gpuTemperature.isEmpty());
    }

    @Test
    void testGetCPUObj() {
        var cpuObj = hardwareInfoService.getCPUObj();

        assertNotNull(cpuObj);
    }

    @Test
    void testGetSysInfoObj() {
        var systemInfo = hardwareInfoService.getSystemInfoObj();

        assertNotNull(systemInfo);
    }

}