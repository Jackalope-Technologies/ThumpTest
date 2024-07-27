/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class HomeController {
    @FXML
    public Button cpuRunBurnTestButton;

    @FXML
    public TextArea logArea;

    @FXML
    protected void onCpuRunBurnTestButtonClick() {
        logArea.appendText("Button was clicked!\r\n");
    }

    @FXML
    protected void onClearButtonClick() {
        logArea.clear();
    }
}