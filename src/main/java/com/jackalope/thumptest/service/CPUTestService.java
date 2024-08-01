/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.service;

import com.jackalope.thumptest.util.testing.CPUBurnTester;
import com.jackalope.thumptest.util.testing.HardwareTester;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CPUTestService {
    private final HardwareInfoService hardwareInfoService;
    private final I18nService i18nService;
    private final List<HardwareTester> workers = new ArrayList<>();

    public CPUTestService() {
        hardwareInfoService = new HardwareInfoService();
        i18nService = new I18nService();
    }

    public void performCPUBurnTest(boolean singleCore) {
        int coresToUse = singleCore ? 1 : hardwareInfoService.getCPULogicalCoreCount();

        log.debug("Performing CPU Burn Test, using {} cores..", coresToUse);

        for (int i = 0; i < coresToUse; i++) {
            CPUBurnTester worker = new CPUBurnTester();
            workers.add(worker);
        }

        workers.forEach(HardwareTester::start);

        log.debug("Finished CPU burn test.");
    }

    public void stopTests() {
        log.debug("Stopping tests, {} workers running..", workers.size());

        workers.forEach(HardwareTester::interrupt);
        workers.clear();

        log.debug("Stopped tests.");
    }
}
