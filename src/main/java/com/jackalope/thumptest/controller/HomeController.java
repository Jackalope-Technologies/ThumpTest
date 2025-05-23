/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.controller;

import com.jackalope.thumptest.ThumpTestApplication;
import com.jackalope.thumptest.service.CPUTestService;
import com.jackalope.thumptest.service.HardwareInfoService;
import com.jackalope.thumptest.service.I18nService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

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

    @FXML
    public MenuItem menuItemHardwareInfo;

    @FXML
    public Label cpuTemp;

    @FXML
    public Label gpuTemp;

    public HomeController() {
        i18nService = new I18nService();
        hardwareInfoService = new HardwareInfoService(i18nService);
        cpuTestService = new CPUTestService(hardwareInfoService, i18nService);
    }

    public void initialize() {
        log.info("Initialising UI..");

        logArea.clear();

        logArea.setText(this.hardwareInfoService.getCpuInfo());
        logArea.appendText(this.hardwareInfoService.getGpuInfo());

        updateTemperatures();

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

        var defaultRuns = 100;

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

    @FXML
    protected void onAboutMenuItemClick() throws IOException {
        log.debug("Menu item about clicked.");

        openAboutDialog();
    }

    @FXML
    protected void onHardwareInfoMenuItemClick() throws IOException {
        log.debug("Menu item hardware information clicked.");

        openHardwareInfoDialog();
    }

    private void closeWindow() {
        Stage stage = (Stage) logArea.getScene().getWindow();
        stage.close();
    }

    private void openAboutDialog() throws IOException {
        openNewView("about-view.fxml", i18nService.getString("title.about"));
    }

    private void openHardwareInfoDialog() throws IOException {
        openNewView("hardware-info-view.fxml", i18nService.getString("title.hardwareInfo"));
    }

    private void openNewView(final String view, final String title) throws IOException {
        var fxmlLoader = new FXMLLoader(ThumpTestApplication.class.getResource(view));
        fxmlLoader.setResources(i18nService.getBundle());
        var scene = new Scene(fxmlLoader.load());
        var stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.getIcons().add(
                new Image(Objects.requireNonNull(ThumpTestApplication.class.getResourceAsStream("logo.png"))));
        stage.show();
    }

    // TODO (#18): Call this every x seconds to update the temperatures live on the UI
    private void updateTemperatures() {
        cpuTemp.setText(hardwareInfoService.getCPUTemperature());
        gpuTemp.setText(hardwareInfoService.getGPUTemperature());
    }
}