/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GraphicsCard;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HardwareInfoServiceTest {
    @Mock
    private I18nService i18nService;

    @InjectMocks
    private HardwareInfoService hardwareInfoService;

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
    void givenGPUIsPresentWhenGetGPUObjIsCalledReturnExpected() {
        GraphicsCard gpuObj = hardwareInfoService.getGPUObj();

        assertNotNull(gpuObj);
    }

    @Test
    void testGetCPULogicalCoreCount() {
        int coreCount = hardwareInfoService.getCPULogicalCoreCount();

        assertTrue(coreCount > 0);
    }

    @Test
    void testGetGPUTemperature_ReturnsNotSupportedMessage() {
        when(i18nService.getString("info.notsupported")).thenReturn("Not supported");

        String result = hardwareInfoService.getGPUTemperature();

        assertEquals("Not supported", result);
        verify(i18nService).getString("info.notsupported");
    }

}