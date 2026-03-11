package com.github.morinator05.ohsheetmusic.controller;

import com.github.morinator05.ohsheetmusic.OhSheetMusicApp;
import com.github.morinator05.ohsheetmusic.database.DatabaseManager;
import com.github.morinator05.ohsheetmusic.model.PieceOfMusic;
import com.github.morinator05.ohsheetmusic.model.Register;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller {

    private Register register;
    private File currentFile;

    ObservableList<PieceOfMusic> musicList = FXCollections.observableArrayList();
    @FXML
    private TableView<PieceOfMusic> tableView;
    @FXML
    private Text textPath;

    @FXML
    private TableColumn<PieceOfMusic, Integer> tableId;
    @FXML
    private TableColumn<PieceOfMusic, String> tableTitle;
    @FXML
    private TableColumn<PieceOfMusic, String> tableNumber;
    @FXML
    private TableColumn<PieceOfMusic, String> tableLetter;

    @FXML
    public void initialize() {

        //TODO: Let the User choose a File
        currentFile = new File("oh-sheet-music.db");
        DatabaseManager.setDatabasePath(currentFile.getAbsolutePath());
        textPath.setText(currentFile.getAbsolutePath());

        this.register = new Register();
        List<PieceOfMusic> dbData = DatabaseManager.getAllPieces();
        register.setContents(dbData);
        System.out.println("Daten geladen: " + register.getContents().size() + " Stücke.");

        musicList = FXCollections.observableArrayList(register.getContents());

        tableId.setCellValueFactory(new PropertyValueFactory<PieceOfMusic, Integer>("id"));
        tableTitle.setCellValueFactory(new PropertyValueFactory<PieceOfMusic, String>("title"));
        tableNumber.setCellValueFactory(new PropertyValueFactory<PieceOfMusic, String>("number"));
        tableLetter.setCellValueFactory(new PropertyValueFactory<PieceOfMusic, String>("letter"));

        tableView.getItems().setAll(register.getContents());
    }

    @FXML
    public void handleSave() {
        System.out.println("Saving data...");
        if (currentFile == null) {
            return;
        }
        List<PieceOfMusic> addedPieces = register.getAddedPieces();
        List<PieceOfMusic> updatedPieces = register.getUpdatedPieces();
        List<PieceOfMusic> removedPieces = register.getRemovedPieces();

        for (PieceOfMusic piece : addedPieces) {
            DatabaseManager.addPiece(piece.getTitle(), piece.getNumber(), piece.getLetter());
        }
        for (PieceOfMusic piece : removedPieces) {
            DatabaseManager.removePiece(piece.getId());
        }
        for (PieceOfMusic piece : updatedPieces) {
            DatabaseManager.updatePiece(piece.getId(), piece.getTitle(), piece.getNumber(), piece.getLetter());
        }
        System.out.println("Saved in: " + currentFile.getAbsolutePath());
    }

    @FXML
    public void handleOpen() {
        //TODO
    }

    @FXML
    void handleNew() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(OhSheetMusicApp.class.getResource("/com/github/morinator05/ohsheetmusic/view/modify-view.fxml"));
            Parent root = fxmlLoader.load();
            ModifyController modifyController = fxmlLoader.getController();

            modifyController.handleModify(null, (piece) -> {
                register.addPiece(piece);
                refreshView();
            });

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Add new piece!");
            stage.setScene(scene);
            stage.sizeToScene();
            stage.setResizable(false);
            modifyController.setStage(stage);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleUpdate() {
        PieceOfMusic selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(OhSheetMusicApp.class.getResource("/com/github/morinator05/ohsheetmusic/view/modify-view.fxml"));
            Parent root = fxmlLoader.load();
            ModifyController modifyController = fxmlLoader.getController();

            modifyController.handleModify(selectedItem, (piece) -> {
                register.updatePiece(piece);
                refreshView();
            });

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Add new piece!");
            stage.setScene(scene);
            stage.sizeToScene();
            stage.setResizable(false);
            modifyController.setStage(stage);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleRemove() {
        PieceOfMusic selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return;

        register.removePiece(selectedItem);
        refreshView();
    }

    private void refreshView() {
        musicList.setAll(register.getContents());
        tableView.setItems(musicList);
        tableView.refresh();
    }

}
