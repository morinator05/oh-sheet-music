package com.github.morinator05.ohsheetmusic.database;

import com.github.morinator05.ohsheetmusic.model.PieceOfMusic;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    DatabaseManager() {
    }

    public static void initDatabase() {
        // SQLite connection string
        var url = "jdbc:sqlite:oh-sheet-music.db";

        // SQL statement for creating a new table
        var sql = "CREATE TABLE IF NOT EXISTS register (" +
                "	id INTEGER PRIMARY KEY," +
                "	title TEXT NOT NULL," +
                "	cabinet_row INTEGER," +
                "	cabinet_column TEXT" + ");";


        try (var conn = DriverManager.getConnection(url); var stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<PieceOfMusic> getAllPieces() {
        //TODO
        return new ArrayList<>();
    }

    public static void addPiece(String title, char cabinet_row, char cabinet_column) {
        //TODO
    }

    public static void removePiece(int id) {
        //TODO
    }

    public static void updatePiece(int id, String newName, char newCabinet_row, char newCabinet_column) {
        //TODO
    }


}
