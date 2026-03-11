package com.github.morinator05.ohsheetmusic.model;

/**
 * This Class represents a piece of Music stored in the Register.
 */
public class PieceOfMusic {

    private final String title;
    private final int id;

    private final String number; // 0-7
    private final String letter; //a-z

    public PieceOfMusic(String title, int id, String number, String letter) {
        this.title = title;
        this.id = id;
        this.number = number;
        this.letter = letter;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getLetter() {
        return letter;
    }
}
