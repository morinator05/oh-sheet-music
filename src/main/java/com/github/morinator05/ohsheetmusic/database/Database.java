package com.github.morinator05.ohsheetmusic.database;

import com.github.morinator05.ohsheetmusic.model.PieceOfMusic;
import java.io.File;
import java.util.List;

/**
 * Interface to persist and manage the sheet music collection
 */
public interface Database {

    /**
     * Initializes the database
     */
    void init();

    /**
     * Retrieves all music pieces stored in the database.
     * @return List of all pieces
     */
    List<PieceOfMusic> getAllPieces();

    /**
     * Sets the storage location for the database.
     * @param file Database file.
     */
    void setFile(File file);

    /**
     * Deletes everything from the database.
     */
    void clear();

    /**
     * Adds a new music piece to the collection.
     */
    void addPiece(String title, String category, String cabinet_row, String cabinet_column);

    /**
     * Updates an existing music piece by its unique ID.
     * @param id The unique identifier of the piece to remove.
     */
    void updatePiece(int id, String newName, String newCategory, String newCabinet_row, String newCabinet_column);

    /**
     * Removes a music piece from the database by its ID.
     * @param id The unique identifier of the piece to remove.
     */
    void removePiece(int id);
}