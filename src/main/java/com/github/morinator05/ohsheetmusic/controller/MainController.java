package com.github.morinator05.ohsheetmusic.controller;

import com.github.morinator05.ohsheetmusic.OhSheetMusicApp;
import com.github.morinator05.ohsheetmusic.database.DatabaseManager;
import com.github.morinator05.ohsheetmusic.model.PieceOfMusic;
import com.github.morinator05.ohsheetmusic.model.Register;
import com.github.morinator05.ohsheetmusic.service.PdfExportService;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class MainController {

    //TODO: add null-checks

    private Register register;
    private final String defaultPath = System.getProperty("user.home") + "/oh-sheet-music.db";
    private File currentFile;
    private Stage stage;

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
    private TableColumn<PieceOfMusic, String> tableCategory;

    @FXML
    public void initialize() {

        //TODO: Let the User choose a File
//        currentFile = new File(defaultPath);
//        DatabaseManager.setFile(currentFile);
//        textPath.setText(currentFile.getAbsolutePath());
//        loadDatabase();

        tableId.setCellValueFactory(new PropertyValueFactory<PieceOfMusic, Integer>("id"));
        tableTitle.setCellValueFactory(new PropertyValueFactory<PieceOfMusic, String>("title"));
        tableNumber.setCellValueFactory(new PropertyValueFactory<PieceOfMusic, String>("number"));
        tableLetter.setCellValueFactory(new PropertyValueFactory<PieceOfMusic, String>("letter"));
        tableCategory.setCellValueFactory(new PropertyValueFactory<PieceOfMusic, String>("category"));

        tableView.setItems(musicList);
        refreshView();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void handleSave() {
        System.out.println("saving: ");
        if (currentFile == null) {
            System.err.println("err: no file selected");
            return;
        }
        for (PieceOfMusic piece : register.getAddedPieces()) {
            DatabaseManager.addPiece(piece.getTitle(), piece.getCategory(), piece.getNumber(), piece.getLetter());
            System.out.println("adding: " + piece.getTitle());
        }
        for (PieceOfMusic piece : register.getRemovedPieces()) {
            DatabaseManager.removePiece(piece.getId());
            System.out.println("removing: " + piece.getTitle());
        }
        for (PieceOfMusic piece : register.getUpdatedPieces()) {
            DatabaseManager.updatePiece(piece.getId(), piece.getTitle(), piece.getCategory(), piece.getNumber(), piece.getLetter());
            System.out.println("updating: " + piece.getTitle());
        }
        register.flushUpdated();
        System.out.println("save: completed at " + currentFile.getAbsolutePath());
    }

    @FXML
    public void handleSaveAs() {

        if (currentFile == null) {
            System.err.println("err: no file selected");
            return;
        }

        //TODO FIX: when saving to a new location different from where the data was loaded from, only the added, removed and updated is exportet to the database
        //maybe just move the file there first.

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Database files (*.db)", "*.db");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(stage);

        System.out.println("selected: " + currentFile.getAbsolutePath());

        if (selectedFile != null) {
            currentFile = selectedFile;
            System.out.println("selected: " + currentFile.getAbsolutePath());
            textPath.setText(currentFile.getAbsolutePath());

            handleSave();
        }
    }

    @FXML
    public void handleOpen() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Database files (*.db)", "*.db");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            currentFile = selectedFile;
            System.out.println("selected: " + currentFile.getAbsolutePath());
            textPath.setText(currentFile.getAbsolutePath());

            loadDatabase();
            refreshView();
        }
    }

    @FXML
    public void handleClose() {
        handleSave();

        register = new Register();
        currentFile = null;

        textPath.setText("select file!");
        musicList.clear();
        System.out.println("info: workspace cleared");
    }

    @FXML
    public void handleExport() {
        if (currentFile == null) {
            System.err.println("err: no file selected");
            return;
        }
        PdfExportService.exportToPdf(System.getProperty("user.home") + "/oh-sheet-music-exported.pdf", register.getContents());
    }

    @FXML
    public void handleQuit() {
        stage.close();
    }

    @FXML
    void handleNew() {
        if (currentFile == null) {
            System.err.println("err: no file selected");
            return;
        }
        openModifyWindow("Add a new piece!", null);
    }

    @FXML
    void handleUpdate() {
        if (currentFile == null) {
            System.err.println("err: no file selected");
            return;
        }
        PieceOfMusic selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            openModifyWindow("Update the selected piece!", selectedItem);
        }
    }

    @FXML
    void handleRemove() {
        if (currentFile == null) {
            System.err.println("err: no file selected");
            return;
        }
        PieceOfMusic selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return;

        register.removePiece(selectedItem);
        refreshView();
    }

    private void refreshView() {
        if(currentFile == null) {
            textPath.setText("please select a file");
            return;
        }
        musicList.setAll(register.getContents());
    }

    private void openModifyWindow(String windowTitle, PieceOfMusic selectedPiece) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(OhSheetMusicApp.class.getResource("/com/github/morinator05/ohsheetmusic/view/modify-view.fxml"));
            Parent root = fxmlLoader.load();
            ModifyController modifyController = fxmlLoader.getController();

            modifyController.handleModify(selectedPiece, (piece) -> {
                if (selectedPiece == null) {
                    register.addPiece(piece);
                } else {
                    register.updatePiece(piece);
                }
                refreshView();
            });
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle(windowTitle);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.setResizable(false);
            modifyController.setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDatabase() {
        if (currentFile == null) {
            System.err.println("err: no file selected");
            return;
        }
        DatabaseManager.setFile(currentFile);
        DatabaseManager.initDatabase();
        this.register = new Register();
        List<PieceOfMusic> dbData = DatabaseManager.getAllPieces();
        register.setContents(dbData);
        System.out.println("load: " + register.getContents().size() + " pieces loaded");
    }

}
