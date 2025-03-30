/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.util.testing;

import java.util.concurrent.atomic.AtomicBoolean;

public class CPUBurnTester extends HardwareTester {
    private Thread worker;
    private AtomicBoolean running = new AtomicBoolean(false);

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

                while (true) {
                    CPUTestUtil.generateLoad(sleepTime);
                    // Thread.sleep here gives a chance for the thread to be interrupted by the UI
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
