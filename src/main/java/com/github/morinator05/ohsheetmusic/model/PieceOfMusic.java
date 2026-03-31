package com.github.morinator05.ohsheetmusic.model;

/**
 * This Class represents a piece of Music stored in the Register.
 */
public class PieceOfMusic {

    private final String title;
    private final int id;
    private final String category;

    public Position pos;

    public PieceOfMusic(String title, int id, String category, String number, String letter) {
        this.title = title;
        this.id = id;
        this.category = category;
        pos = new Position(number, letter);
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }
}
