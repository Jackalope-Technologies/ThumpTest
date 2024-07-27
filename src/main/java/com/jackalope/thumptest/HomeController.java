package com.jackalope.thumptest;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeController {
    @FXML
    public Button welcomeButton;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onWelcomeButtonClick() {
        welcomeText.setText("Welcome to ThumpTest!");
        welcomeButton.setVisible(false);
    }
}