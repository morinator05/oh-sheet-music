package com.github.morinator05.ohsheetmusic.database;

import com.github.morinator05.ohsheetmusic.model.PieceOfMusic;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static String dbPath = "jdbc:sqlite:oh-sheet-music.db"; //default path for database

    DatabaseManager() {
    }

    public static void setDatabasePath(String newPath) {
        dbPath = "jdbc:sqlite:" + newPath;
    }

    public static void initDatabase() {
        // SQL statement for creating a new table
        var sql = "CREATE TABLE IF NOT EXISTS register (" +
                "	id INTEGER PRIMARY KEY," +
                "	title TEXT NOT NULL," +
                "   category TEXT NOT NULL," +
                "	cabinet_row INTEGER," +
                "	cabinet_column TEXT" + ");";

        try (var conn = DriverManager.getConnection(dbPath); var stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void clearDatabase() {
        var sql = "DELETE FROM register";
        try (var conn = DriverManager.getConnection(dbPath); var stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<PieceOfMusic> getAllPieces() {

        List<PieceOfMusic> pieces = new ArrayList<>();
        var sql = "SELECT * from register";

        try (var conn = DriverManager.getConnection(dbPath);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PieceOfMusic p = new PieceOfMusic(
                        rs.getString("title"),
                        rs.getInt("id"),
                        rs.getString("category"),
                        rs.getString("cabinet_row"),
                        rs.getString("cabinet_column")
                );
                pieces.add(p);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return pieces;
    }

    public static void addPiece(String title, String category, String cabinet_row, String cabinet_column) {
        var sql = "INSERT INTO register(title, category, cabinet_row, cabinet_column) VALUES(?, ?, ?, ?)";

        try (var conn = DriverManager.getConnection(dbPath);
             var pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, category);
            pstmt.setString(3, cabinet_row);
            pstmt.setString(4, cabinet_column);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error adding piece: " + e.getMessage());
        }
    }

    public static void removePiece(int id) {
        var sql = "DELETE FROM register WHERE id = ?";

        try (var conn = DriverManager.getConnection(dbPath);
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void updatePiece(int id, String newName, String newCategory, String newCabinet_row, String newCabinet_column) {
        var sql = "UPDATE register SET title = ?, category = ?, cabinet_row = ?, cabinet_column = ? WHERE id = ?";

        try (var conn = DriverManager.getConnection(dbPath);
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setString(2, newCategory);
            pstmt.setString(3, newCabinet_row);
            pstmt.setString(4, newCabinet_column);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
