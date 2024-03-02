package com.example.labyrinth_runner;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameApplicationController {
    @FXML
    private Label welcomeText;

    private GameApplication myApp;

    public void setMainApp(GameApplication myApp) {
        this.myApp = myApp;
    }

    @FXML
    protected void onStartNewGameButtonClick() {
        myApp.openMainWindow(true);
    }

    @FXML
    protected void onContinueButtonClick() {
        myApp.openMainWindow(false);
    }
}