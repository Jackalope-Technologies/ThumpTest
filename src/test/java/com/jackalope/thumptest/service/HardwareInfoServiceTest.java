/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HardwareInfoServiceTest {
    private I18nService i18nService;
    private HardwareInfoService hardwareInfoService;

    @BeforeEach
    void setup() {
        i18nService = new I18nService();
        hardwareInfoService = new HardwareInfoService(i18nService);
    }

    @Test
    void testGetCpuInfo() {
        String cpuInfo = hardwareInfoService.getCpuInfo();

        assertFalse(cpuInfo.isEmpty());
    }

    @Test
    void testGetGpuInfo() {
        String gpuInfo = hardwareInfoService.getGpuInfo();

        assertFalse(gpuInfo.isEmpty());
    }

    @Test
    void testGetCPUTemperature() {
        String cpuTemperature = hardwareInfoService.getCPUTemperature();

        assertFalse(cpuTemperature.isEmpty());
    }

    @Test
    void testGetGPUTemperature() {
        String gpuTemperature = hardwareInfoService.getCPUTemperature();

        assertFalse(gpuTemperature.isEmpty());
    }

    @Test
    void testGetCPUObj() {
        CentralProcessor cpuObj = hardwareInfoService.getCPUObj();

        assertNotNull(cpuObj);
    }

    @Test
    void testGetSysInfoObj() {
        SystemInfo systemInfo = hardwareInfoService.getSystemInfoObj();

        assertNotNull(systemInfo);
    }

}