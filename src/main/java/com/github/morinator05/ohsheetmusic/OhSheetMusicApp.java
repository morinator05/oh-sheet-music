package com.github.morinator05.ohsheetmusic;

import atlantafx.base.theme.PrimerDark;
import com.github.morinator05.ohsheetmusic.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class OhSheetMusicApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        String fxmlPath = "/com/github/morinator05/ohsheetmusic/view/main-view.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(OhSheetMusicApp.class.getResource(fxmlPath));

        Scene scene = new Scene(fxmlLoader.load());

        MainController mainController = fxmlLoader.getController();

//        String css = getClass().getResource("/com/github/morinator05/ohsheetmusic/view/dark-purple.css").toExternalForm();
//        scene.getStylesheets().add(css);

        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

        stage.getIcons().addAll(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/github/morinator05/ohsheetmusic/icons/icon32.png"))),
                new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/github/morinator05/ohsheetmusic/icons/icon64.png")))
        );

        stage.setTitle("OhSheetMusic");

        mainController.setStage(stage);

        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();

    }
}
