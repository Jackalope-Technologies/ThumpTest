/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest;

import com.jackalope.thumptest.service.I18nService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class ThumpTestApplication extends Application {
    @Override
    public void start(final Stage stage) throws IOException {
        log.info("Starting application..");

        var i18nService = new I18nService();

        var fxmlLoader = new FXMLLoader(ThumpTestApplication.class.getResource("home-view.fxml"));
        fxmlLoader.setResources(i18nService.getBundle());
        var scene = new Scene(fxmlLoader.load());
        stage.setTitle("ThumpTest");
        stage.setScene(scene);
        stage.getIcons().add(
                new Image(Objects.requireNonNull(ThumpTestApplication.class.getResourceAsStream("logo.png"))));
        stage.show();

        log.info("Application started.");
    }

    public static void main(final String[] args) {
        launch();
    }
}