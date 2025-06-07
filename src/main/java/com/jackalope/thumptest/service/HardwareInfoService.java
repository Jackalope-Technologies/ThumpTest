/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.service;

import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class HardwareInfoService {
    private final SystemInfo systemInfo;
    private final I18nService i18nService;
    private final HardwareAbstractionLayer hardwareInfo;
    private final CentralProcessor processor;
    private final GraphicsCard graphicsCard;

    public HardwareInfoService(I18nService i18nService) {
        this.systemInfo = new SystemInfo();
        this.i18nService = i18nService;
        this.hardwareInfo = systemInfo.getHardware();
        this.processor = hardwareInfo.getProcessor();
        this.graphicsCard = getMostLikelyGPU(hardwareInfo.getGraphicsCards());
    }

    public static String twoDecimalPlaces(double value) {
        var df = new DecimalFormat("0.00##");

        return df.format(value);
    }

    public CentralProcessor getCPUObj() {
        return processor;
    }

    public SystemInfo getSystemInfoObj() {
        return systemInfo;
    }

    public GraphicsCard getGPUObj() {
        return graphicsCard;
    }

    public String getCpuInfo() {
        double frequencyGHz = processor.getMaxFreq() / 1_000_000_000.0;
        return String.format("CPU: %s Logical cores: %d Threads: %d @ %sGHz\r\n",
                processor.getProcessorIdentifier().getName(),
                processor.getPhysicalProcessorCount(),
                processor.getLogicalProcessorCount(),
                twoDecimalPlaces(frequencyGHz));
    }

    public int getCPULogicalCoreCount() {
        return processor.getLogicalProcessorCount();
    }

    public String getGpuInfo() {
        if (null == graphicsCard) {
            return "No GPU found";
        } else {
            return "GPU: " + graphicsCard.getName() + " Software Version: " + graphicsCard.getVersionInfo() + "\r\n";
        }
    }

    public String getCPUTemperature() {
        double cpuTemperature = hardwareInfo.getSensors().getCpuTemperature();

        log.info("CPU Temperature: {}", cpuTemperature);

        return twoDecimalPlaces(cpuTemperature) + "°C";
    }

    public String getGPUTemperature() {
        return i18nService.getString("info.notsupported");
    }

    private GraphicsCard getMostLikelyGPU(final List<GraphicsCard> graphicsCards) {
        // If there are multiple GPUs, return the one with the highest VRAM, it's likely the primary GPU
        return graphicsCards.stream()
                .max(Comparator.comparingLong(GraphicsCard::getVRam))
                .orElse(null);
    }
}
