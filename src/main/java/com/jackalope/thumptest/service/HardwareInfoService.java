/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.service;

import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;

import java.text.DecimalFormat;
import java.util.List;

import static java.lang.Long.valueOf;

@Slf4j
public class HardwareInfoService {
    private final SystemInfo systemInfo;
    private final I18nService i18nService;
    private final HardwareAbstractionLayer hardwareInfo;
    private final CentralProcessor processor;

    public HardwareInfoService() {
        systemInfo = new SystemInfo();
        i18nService = new I18nService();
        hardwareInfo = systemInfo.getHardware();
        processor = hardwareInfo.getProcessor();
    }

    public static String twoDecimalPlaces(double value) {
        DecimalFormat df = new DecimalFormat("0.00##");

        return df.format(value);
    }

    public CentralProcessor getCPUObj() {
        return processor;
    }

    public SystemInfo getSystemInfoObj() {
        return systemInfo;
    }

    public String getCpuInfo() {
        double frequencyGHzDouble = valueOf(processor.getMaxFreq()).doubleValue() / 1000000000;
        String frequencyGHz = twoDecimalPlaces(frequencyGHzDouble);

        return "CPU: " + processor.getProcessorIdentifier().getName() + " Logical cores: "
                + processor.getPhysicalProcessorCount() + " Threads: " + processor.getLogicalProcessorCount()
                + " @ " + frequencyGHz + "GHz\r\n";
    }

    public int getCPULogicalCoreCount() {
        return processor.getLogicalProcessorCount();
    }

    public String getGpuInfo() {
        HardwareAbstractionLayer hardwareInfo = systemInfo.getHardware();
        List<GraphicsCard> graphicsCards = hardwareInfo.getGraphicsCards();
        GraphicsCard gpu1 = graphicsCards.getLast();

        return "GPU: " + gpu1.getName() + " Software Version: " + gpu1.getVersionInfo() + "\r\n";
    }

    public String getCPUTemperature() {
        double cpuTemperature = hardwareInfo.getSensors().getCpuTemperature();

        log.info("CPU Temperature: {}", cpuTemperature);

        return twoDecimalPlaces(cpuTemperature) + "°C";
    }

    public String getGPUTemperature() {
        return i18nService.getString("info.notsupported");
    }
}
