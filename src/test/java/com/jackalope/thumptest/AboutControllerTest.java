/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

@ExtendWith(ApplicationExtension.class)
class AboutControllerTest {
    private Scene scene;

    @Start
    private void start(final Stage stage) throws IOException {
        var fxmlLoader = new FXMLLoader(ThumpTestApplication.class.getResource("about-view.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("Strings");
        fxmlLoader.setResources(bundle);
        scene = new Scene(fxmlLoader.load());

        stage.setTitle("ThumpTest: About");
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test
    void hasClearButton() {
        FxAssert.verifyThat("#buttonClose", LabeledMatchers.hasText("Close"));
    }

    @Test
    void hasVersionLabel() {
        FxAssert.verifyThat("#versionLabel", LabeledMatchers.hasText("Version:"));
    }

    @Test
    void hasHomepageLabel() {
        FxAssert.verifyThat("#homepageLabel", LabeledMatchers.hasText("Homepage:"));
    }

    @Test
    void hasAboutTitle() {
        FxAssert.verifyThat("#aboutTitle", LabeledMatchers.hasText("ThumpTest"));
    }

    @Test
    void ensureCloseBehavesAsExpected(final FxRobot robot) {
        robot.clickOn("#buttonClose");

        assertNotNull(scene);
    }

}