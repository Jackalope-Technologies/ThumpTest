/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.controller;

import com.jackalope.thumptest.service.HardwareInfoService;
import com.jackalope.thumptest.service.I18nService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
        log.info("Initialising UI..");

        logArea.setText(this.hardwareInfoService.getCpuInfo());
        logArea.appendText(this.hardwareInfoService.getGpuInfo());

        log.info("UI initialised.");
    }

    @FXML
    protected void onCpuRunBurnTestButtonClick() {
        log.debug("CPU Run Burn Test button clicked.");
        logArea.appendText(i18nService.getString("text.buttonWasClicked") + "\r\n");
    }

    @FXML
    protected void onClearButtonClick() {
        log.debug("Clear button clicked.");

        logArea.clear();
    }
}