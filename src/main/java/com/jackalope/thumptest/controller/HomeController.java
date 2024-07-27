/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.controller;

import com.jackalope.thumptest.service.HardwareInfoService;
import com.jackalope.thumptest.service.I18nService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class HomeController {
    private final HardwareInfoService hardwareInfoService;
    private final I18nService i18nService;

    @FXML
    public Button cpuRunBurnTestButton;

    @FXML
    public TextArea logArea;

    public HomeController() {
        this.hardwareInfoService = new HardwareInfoService();
        i18nService = new I18nService();
    }

    public void initialize() {
        logArea.setText(this.hardwareInfoService.getCpuInfo());
        logArea.appendText(this.hardwareInfoService.getGpuInfo());
    }

    @FXML
    protected void onCpuRunBurnTestButtonClick() {
        logArea.appendText(i18nService.getString("buttonWasClicked") + "\r\n");
    }

    @FXML
    protected void onClearButtonClick() {
        logArea.clear();
    }
}