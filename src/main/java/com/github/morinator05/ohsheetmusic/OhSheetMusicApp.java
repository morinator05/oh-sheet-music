package com.github.morinator05.ohsheetmusic;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.IOException;

public class OhSheetMusicApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Group root = new Group();
        Scene scene = new Scene(root, Color.DARKGREY);

        stage.setTitle("OhMySheetMusic");

        stage.setScene(scene);
        stage.show();

    }
}
