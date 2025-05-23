/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.IOException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ApplicationExtension.class)
class HomeControllerTest {
    private Scene scene;

    @Start
    private void start(final Stage stage) throws IOException {
        var fxmlLoader = new FXMLLoader(ThumpTestApplication.class.getResource("home-view.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("Strings");
        fxmlLoader.setResources(bundle);
        scene = new Scene(fxmlLoader.load());

        stage.setTitle("ThumpTest");
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test
    void hasClearButton() {
        FxAssert.verifyThat("#clearButton", LabeledMatchers.hasText("Clear"));
    }

    @Test
    void hasRunCPUBurnButton() {
        FxAssert.verifyThat("#cpuRunBurnTestButton", LabeledMatchers.hasText("Run CPU Burn Test"));
    }

    @Test
    void hasRunCPUBenchButton() {
        FxAssert.verifyThat("#cpuRunBenchTestButton", LabeledMatchers.hasText("Run CPU Bench Test"));
    }

    @Test
    void hasStopTestsButton() {
        FxAssert.verifyThat("#stopTestsButton", LabeledMatchers.hasText("Stop Tests"));
    }

    @Test
    void ensureClearButtonBehavesAsExpected(final FxRobot robot) {
        robot.clickOn("#clearButton");

        TextArea logArea = (TextArea) scene.lookup("#logArea");
        assertTrue(logArea.getText().startsWith("CPU: "));
    }

    @Test
    void ensureFileCloseBehavesAsExpected(final FxRobot robot) {
        robot.clickOn("#menuItemFile");
        robot.clickOn("#menuItemClose");

        assertNotNull(scene);
    }

    @Test
    void hasCPUTempLabel() {
        FxAssert.verifyThat("#cpuTempLabel", LabeledMatchers.hasText("CPU Temp:"));
    }

    @Test
    void hasGPUTempLabel() {
        FxAssert.verifyThat("#gpuTempLabel", LabeledMatchers.hasText("GPU Temp:"));
    }

}