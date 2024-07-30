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

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ApplicationExtension.class)
class ThumpTestApplicationTest {
    private Scene scene;

    @Start
    private void start(final Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ThumpTestApplication.class.getResource("home-view.fxml"));
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
    void ensureRunCPUBurnButtonBehavesAsExpected(final FxRobot robot) {
        var expectedText = "Button was clicked!";

        robot.clickOn("#cpuRunBurnTestButton");

        TextArea logArea = (TextArea) scene.lookup("#logArea");
        assertTrue(logArea.getText().contains(expectedText));
    }

    @Test
    void ensureClearButtonBehavesAsExpected(final FxRobot robot) {
        for (var i = 0; i <= 5; i++) {
            robot.clickOn("#cpuRunBurnTestButton");
        }

        robot.clickOn("#clearButton");

        TextArea logArea = (TextArea) scene.lookup("#logArea");
        assertTrue(logArea.getText().isEmpty());
    }

}