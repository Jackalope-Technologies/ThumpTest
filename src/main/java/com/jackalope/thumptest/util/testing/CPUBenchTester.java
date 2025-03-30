/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.util.testing;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class CPUBenchTester extends HardwareTester {
    private Thread worker;
    private AtomicBoolean running = new AtomicBoolean(false);

    @Setter
    private int runs = 100;

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    public void interrupt() {
        running.set(false);

        if (worker != null){
            worker.interrupt();
        }
    }

    public void run() {
        running.set(true);

        while (running.get()) {
            try {
                var milliseconds = 500;
                long sleepTime = milliseconds * 1000000L; // convert to nanoseconds

                for (var i = 0; i < runs; i++) {
                    log.trace("Run #{}", i);
                    CPUTestUtil.generateLoad(sleepTime);
                    // Thread.sleep here gives a chance for the thread to be interrupted by the UI
                    Thread.sleep(1);
                }

                running.set(false);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
