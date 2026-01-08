package com.example.szunetnapok;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;


public class SzunetnapokController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}