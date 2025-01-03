/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest;

import com.jackalope.thumptest.service.I18nService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ThumpTestApplication extends Application {
    @Override
    public void start(final Stage stage) throws IOException {
        log.info("Starting application..");

        I18nService i18nService = new I18nService();

        FXMLLoader fxmlLoader = new FXMLLoader(ThumpTestApplication.class.getResource("home-view.fxml"));
        fxmlLoader.setResources(i18nService.getBundle());
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ThumpTest");
        stage.setScene(scene);
        stage.show();

        log.info("Application started.");
    }

    public static void main(final String[] args) {
        launch();
    }
}