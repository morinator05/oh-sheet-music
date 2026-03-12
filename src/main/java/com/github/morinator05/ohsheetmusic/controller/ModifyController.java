package com.github.morinator05.ohsheetmusic.controller;

import com.github.morinator05.ohsheetmusic.model.PieceOfMusic;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class ModifyController {

    @FXML
    private TextField fieldTitle;
    @FXML
    private TextField fieldNumber;
    @FXML
    private TextField fieldLetter;
    @FXML
    private TextField fieldCategory;

    private Stage stage;

    private Consumer<PieceOfMusic> onSave;
    private PieceOfMusic currentPiece;

    @FXML
    public void initialize() {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void handleModify(PieceOfMusic selected, Consumer<PieceOfMusic> callback) {
        this.currentPiece = selected;
        this.onSave = callback;
        if (selected != null) {
            fieldTitle.setText(selected.getTitle());
            fieldNumber.setText(selected.getNumber());
            fieldLetter.setText(selected.getLetter());
            fieldCategory.setText(selected.getCategory());
        }
    }

    @FXML
    public void onSaveClicked() {
        String title = fieldTitle.getText();
        String number = fieldNumber.getText();
        String letter = fieldLetter.getText();
        String category = fieldCategory.getText();

        int id;
        if (currentPiece != null) {
            id = currentPiece.getId();
        } else {
            id = 0;
        }
        PieceOfMusic result = new PieceOfMusic(title, id, category, number, letter);

        onSave.accept(result);

        if (stage != null) {
            stage.close();
        }
    }

    @FXML
    public void onCancelClicked() {
        if (stage != null) {
            stage.close();
        }
    }
}
