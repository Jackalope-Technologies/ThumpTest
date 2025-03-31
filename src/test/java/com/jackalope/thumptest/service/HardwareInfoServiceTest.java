/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import static org.junit.jupiter.api.Assertions.*;

class HardwareInfoServiceTest {
    @Mock
    private I18nService i18nService;

    @InjectMocks
    private HardwareInfoService hardwareInfoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCpuInfo() {
        String cpuInfo = hardwareInfoService.getCpuInfo();

        assertFalse(cpuInfo.isEmpty());
    }

    @Test
    void givenGPUIsPresentTestGetGpuInfo() {
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

    @Test
    void testGetCPULogicalCoreCount() {
        int coreCount = hardwareInfoService.getCPULogicalCoreCount();

        assertTrue(coreCount > 0);
    }

}