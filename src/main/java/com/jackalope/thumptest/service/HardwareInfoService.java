/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.service;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;

import java.text.DecimalFormat;
import java.util.List;

import static java.lang.Long.valueOf;

public class HardwareInfoService {
    private final SystemInfo systemInfo;

    public HardwareInfoService() {
        systemInfo = new SystemInfo();
    }

    public static String twoDecimalPlaces(double value) {
        DecimalFormat df = new DecimalFormat("0.00##");

        return df.format(value);
    }

    public String getCpuInfo() {
        HardwareAbstractionLayer hardwareInfo = systemInfo.getHardware();
        CentralProcessor processor = hardwareInfo.getProcessor();

        double frequencyGHzDouble = valueOf(processor.getMaxFreq()).doubleValue() / 1000000000;
        String frequencyGHz = twoDecimalPlaces(frequencyGHzDouble);

        return "CPU: " + processor.getProcessorIdentifier() + " Logical cores: "
                + processor.getPhysicalProcessorCount() + " Threads: " + processor.getLogicalProcessorCount()
                + " @ " + frequencyGHz + "GHz\r\n";
    }

    public String getGpuInfo() {
        HardwareAbstractionLayer hardwareInfo = systemInfo.getHardware();
        List<GraphicsCard> graphicsCards = hardwareInfo.getGraphicsCards();
        GraphicsCard gpu1 = graphicsCards.getFirst();

        return "GPU: " + gpu1.getName() + " Software Version: " + gpu1.getVersionInfo() + "\r\n";
    }
}
