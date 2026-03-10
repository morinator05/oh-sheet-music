module com.github.morinator05.ohsheetmusic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.github.morinator05.ohsheetmusic to javafx.fxml;
    exports com.github.morinator05.ohsheetmusic;
}