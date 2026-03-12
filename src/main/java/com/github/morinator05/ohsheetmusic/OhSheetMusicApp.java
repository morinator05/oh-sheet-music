package com.github.morinator05.ohsheetmusic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.IOException;

public class OhSheetMusicApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        String fxmlPath = "/com/github/morinator05/ohsheetmusic/view/main-view.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(OhSheetMusicApp.class.getResource(fxmlPath));

        Scene scene = new Scene(fxmlLoader.load());

        String css = getClass().getResource("/com/github/morinator05/ohsheetmusic/view/style.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("OhMySheetMusic");

        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();

    }
}
