/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.util.testing;

public abstract class HardwareTester implements Runnable {
    abstract public void start();
    abstract public void interrupt();
}
