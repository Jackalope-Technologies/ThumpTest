/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

}