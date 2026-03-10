package com.github.morinator05.ohsheetmusic.model;

import com.github.morinator05.ohsheetmusic.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class Register {

    private List<PieceOfMusic> contents;
    private List<PieceOfMusic> addedPieces;
    private List<PieceOfMusic> removedPieces;
    private List<PieceOfMusic> updatedPieces;

    Register() {
        this.contents = new ArrayList<>();
        this.addedPieces = new ArrayList<>();
        this.removedPieces = new ArrayList<>();
        this.updatedPieces = new ArrayList<>();
    }

    public void save() {
        for (PieceOfMusic piece : addedPieces) {
            DatabaseManager.addPiece(piece.getName(), piece.getNumber(), piece.getLetter());
        }
        for (PieceOfMusic piece : removedPieces) {
            DatabaseManager.removePiece(piece.getId());
        }
        for (PieceOfMusic piece : updatedPieces) {
            DatabaseManager.updatePiece(piece.getId(), piece.getName(), piece.getNumber(), piece.getLetter());
        }
        clearChanges();
    }

    public void load(){
        this.contents = DatabaseManager.getAllPieces();
        clearChanges();
    }

    public List<PieceOfMusic> getContents() {
        return contents;
    }

    private void clearChanges() {
        addedPieces.clear();
        removedPieces.clear();
        updatedPieces.clear();
    }
}
