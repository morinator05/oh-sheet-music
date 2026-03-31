package com.github.morinator05.ohsheetmusic.model;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Register {

    private final List<PieceOfMusic> contents;
    private final List<PieceOfMusic> addedPieces;
    private final List<PieceOfMusic> removedPieces;
    private final List<PieceOfMusic> updatedPieces;
    private static final HashSet<Position> positions = new HashSet<>();

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
        for (PieceOfMusic piece: pieces) {
            positions.add(piece.pos);
        }
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
        contents.add(piece);
        positions.add(piece.pos);
    }

    public void removePiece(PieceOfMusic piece) {
        removedPieces.add(piece);
        contents.removeIf(p -> p.getId() == piece.getId());
        positions.remove(piece.pos);
    }

    public void updatePiece(PieceOfMusic piece) {
        updatedPieces.add(piece);
        contents.removeIf(p -> p.getId() == piece.getId());
        contents.add(piece);
    }

    public void flushUpdated() {
        this.addedPieces.clear();
        this.removedPieces.clear();
        this.updatedPieces.clear();
    }

    public static Position findEmptySlot() {
        char number;
        char letter;

        for (number = '1'; number <= '9'; number ++) {

            for(letter = 'A'; letter <= 'Z'; letter++) {

                Position temp = new Position(String.valueOf(number), String.valueOf(letter));
                if (!positions.contains(temp)) {
                    return temp;
                }

            }

        }
        return null;
    }

}
