/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.controller;

import com.jackalope.thumptest.service.HardwareInfoService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class HomeController {
    private final HardwareInfoService hardwareInfoService;

    @FXML
    public Button cpuRunBurnTestButton;

    @FXML
    public TextArea logArea;

    public HomeController() {
        this.hardwareInfoService = new HardwareInfoService();
    }

    public void initialize() {
        logArea.setText(this.hardwareInfoService.getCpuInfo());
        logArea.appendText(this.hardwareInfoService.getGpuInfo());
    }

    @FXML
    protected void onCpuRunBurnTestButtonClick() {
        logArea.appendText("Button was clicked!\r\n");
    }

    @FXML
    protected void onClearButtonClick() {
        logArea.clear();
    }
}