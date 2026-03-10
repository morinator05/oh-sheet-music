module com.github.morinator05.ohsheetmusic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.github.morinator05.ohsheetmusic to javafx.fxml;
    exports com.github.morinator05.ohsheetmusic;
    exports com.github.morinator05.ohsheetmusic.database;
    exports com.github.morinator05.ohsheetmusic.model;
}