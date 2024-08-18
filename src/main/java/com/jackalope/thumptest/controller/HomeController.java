/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.controller;

import com.jackalope.thumptest.service.CPUTestService;
import com.jackalope.thumptest.service.HardwareInfoService;
import com.jackalope.thumptest.service.I18nService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HomeController {
    private final HardwareInfoService hardwareInfoService;
    private final I18nService i18nService;
    private final CPUTestService cpuTestService;

    @FXML
    public Button cpuRunBurnTestButton;
    @FXML
    public Button cpuRunBenchTestButton;

    @FXML
    public TextArea logArea;

    @FXML
    public MenuItem menuItemClose;

    public HomeController() {
        hardwareInfoService = new HardwareInfoService();
        i18nService = new I18nService();
        cpuTestService = new CPUTestService();
    }

    public void initialize() {
        log.info("Initialising UI..");

        logArea.clear();

        logArea.setText(this.hardwareInfoService.getCpuInfo());
        logArea.appendText(this.hardwareInfoService.getGpuInfo());

        log.info("UI initialised.");
    }

    @FXML
    protected void onCpuRunBurnTestButtonClick() {
        log.debug("CPU Run Burn Test button clicked.");
        logArea.appendText(i18nService.getString("text.performCPUBurnTest") + "\r\n");

        cpuTestService.performCPUBurnTest(false);

        logArea.appendText(i18nService.getString("text.finishCPUBurnTest") + "\r\n");
    }

    @FXML
    protected void onCpuRunBenchTestButtonClick() throws InterruptedException {
        log.debug("CPU Run Bench Test button clicked.");

        int defaultRuns = 100;

        Object[] performMessageArgs = {
                defaultRuns
        };

        logArea.appendText(i18nService.getFormattedString("text.performCPUBenchTest", performMessageArgs) + "\r\n");

        long timeTaken = cpuTestService.performCPUBenchTest(false, defaultRuns);

        Object[] finishMessageArgs = {
                defaultRuns,
                timeTaken
        };

        logArea.appendText(i18nService.getFormattedString("text.finishCPUBenchTest", finishMessageArgs) + "\r\n");
    }

    @FXML
    protected void onClearButtonClick() {
        log.debug("Clear button clicked.");

        initialize();
    }

    @FXML
    protected void onStopTestsButtonClick() {
        log.debug("Stop Tests button clicked.");

        logArea.appendText(i18nService.getString("text.stopTests") + "\r\n");

        cpuTestService.stopTests();
    }

    @FXML
    protected void onCloseMenuItemClick() {
        log.debug("Menu item close clicked.");

        closeWindow();

        log.debug("Application closing..");
    }

    private void closeWindow() {
        Stage stage = (Stage) logArea.getScene().getWindow();
        stage.close();
    }
}