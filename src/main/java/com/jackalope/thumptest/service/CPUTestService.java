/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.service;

import com.jackalope.thumptest.util.testing.CPUBenchTester;
import com.jackalope.thumptest.util.testing.CPUBurnTester;
import com.jackalope.thumptest.util.testing.HardwareTester;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class CPUTestService {
    private final HardwareInfoService hardwareInfoService;
    private final I18nService i18nService;
    private final List<HardwareTester> workers = new CopyOnWriteArrayList<>();
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public CPUTestService(HardwareInfoService hardwareInfoService, I18nService i18nService) {
        this.hardwareInfoService = hardwareInfoService;
        this.i18nService = i18nService;
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

    public long performCPUBenchTest(boolean singleCore, int runs) throws InterruptedException {
        int coresToUse = singleCore ? 1 : hardwareInfoService.getCPULogicalCoreCount();

        log.debug("Performing CPU Bench Test, using {} cores and performing {} runs..", coresToUse, runs);

        executor = Executors.newFixedThreadPool(coresToUse);

        for (int i = 0; i < coresToUse; i++) {
            CPUBenchTester worker = new CPUBenchTester();
            worker.setRuns(runs);
            workers.add(worker);
        }

        List<Callable<Object>> callableWorkers = workers.stream().map(Executors::callable).toList();

        long startTime = System.nanoTime();

        executor.invokeAll(callableWorkers);

        awaitTerminationAfterShutdown(executor);

        long endTime = System.nanoTime();
        long duration = TimeUnit.NANOSECONDS.toSeconds(endTime - startTime);

        stopTests();

        log.debug("Finished CPU bench test {} runs in {}s.", runs, duration);

        return duration;
    }

    public void stopTests() {
        log.debug("Stopping tests, {} workers running..", workers.size());

        if (executor != null) {
            executor.shutdownNow();
            executor.close();
        }

        workers.forEach(HardwareTester::interrupt);
        workers.clear();

        log.debug("Stopped tests.");
    }

    protected List<HardwareTester> getTestWorkers() {
        return this.workers;
    }

    private void awaitTerminationAfterShutdown(final ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(6, TimeUnit.HOURS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
