/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.controller;

import com.jackalope.thumptest.service.ApplicationInfoService;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class AboutController {
    private final ApplicationInfoService applicationInfoService;
    @FXML
    public Label aboutVersion;

    @FXML
    public Hyperlink aboutHomepage;

    public AboutController() {
        applicationInfoService = new ApplicationInfoService();
    }

    public void initialize() {
        log.info("Initialising About UI..");

        aboutVersion.setText(applicationInfoService.getVersion());
        aboutHomepage.setText(applicationInfoService.getHomepage());

        log.info("About UI initialised.");
    }

    @FXML
    protected void closeWindow() {
        Stage stage = (Stage) aboutVersion.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onHomepageClicked() throws URISyntaxException, IOException {
        log.info("Homepage clicked..");
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            log.info("Opening web browser..");
            Desktop.getDesktop().browse(new URI(applicationInfoService.getHomepage()));
        } else {
            log.warn("Couldn't open web browser.");
        }
    }
}