package com.github.morinator05.ohsheetmusic.model;

import com.github.morinator05.ohsheetmusic.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class Register {

    private final List<PieceOfMusic> contents;
    private final List<PieceOfMusic> addedPieces;
    private final List<PieceOfMusic> removedPieces;
    private final List<PieceOfMusic> updatedPieces;

    public Register() {
        this.contents = new ArrayList<>();
        this.addedPieces = new ArrayList<>();
        this.removedPieces = new ArrayList<>();
        this.updatedPieces = new ArrayList<>();
    }

    public List<PieceOfMusic> getContents() {
        return contents;
    }

    public void setContents(List<PieceOfMusic> pieces) {
        this.contents.clear();
        this.contents.addAll(pieces);
    }

    public List<PieceOfMusic> getAddedPieces() {
        return addedPieces;
    }

    public List<PieceOfMusic> getUpdatedPieces() {
        return updatedPieces;
    }

    public List<PieceOfMusic> getRemovedPieces() {
        return removedPieces;
    }

    public void addPiece(PieceOfMusic piece) {
        addedPieces.add(piece);
    }

    public void removePiece(PieceOfMusic piece) {
        removedPieces.add(piece);
    }

    public void updatePiece(PieceOfMusic piece) {
        updatedPieces.add(piece);
    }

}
