package com.example.szunetnapok;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SzunetnapokApplication extends Application {

    public static boolean isRunningTest = false;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = null;
        if (!isRunningTest) fxmlLoader = new FXMLLoader(SzunetnapokApplication.class.getResource("szunetnapok-view.fxml"));
        Scene scene = null;
        if (!isRunningTest) scene = new Scene(fxmlLoader.load(), 800, 600);
        if (!isRunningTest) stage.setTitle("Munkaszüneti napok");
        if (!isRunningTest) stage.setScene(scene);
        if (!isRunningTest) stage.show();
    }
}