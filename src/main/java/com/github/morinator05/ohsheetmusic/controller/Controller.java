package com.github.morinator05.ohsheetmusic.controller;

import com.github.morinator05.ohsheetmusic.database.DatabaseManager;
import com.github.morinator05.ohsheetmusic.model.PieceOfMusic;
import com.github.morinator05.ohsheetmusic.model.Register;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class Controller {

    private Register register;

    ObservableList<PieceOfMusic> musicList = FXCollections.observableArrayList();
    @FXML private TableView<PieceOfMusic> tableView;

    @FXML private TableColumn<PieceOfMusic, Integer> tableId;
    @FXML private TableColumn<PieceOfMusic, String> tableTitle;

    @FXML
    public void initialize() {
        this.register = new Register();
        List<PieceOfMusic> dbData = DatabaseManager.getAllPieces();
        register.setContents(dbData);
        System.out.println("Daten geladen: " + register.getContents().size() + " Stücke.");

        musicList = FXCollections.observableArrayList(register.getContents());

        tableId.setCellValueFactory(new PropertyValueFactory<PieceOfMusic, Integer>("id"));
        tableTitle.setCellValueFactory(new PropertyValueFactory<PieceOfMusic, String>("title"));

        tableView.getItems().setAll(register.getContents());
    }

    @FXML
    public void handleSave() {
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
    }

    @FXML
    public void handleSort() {

    }
}