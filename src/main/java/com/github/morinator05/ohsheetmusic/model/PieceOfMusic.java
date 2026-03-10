package com.github.morinator05.ohsheetmusic.model;

/**
 * This Class represents a piece of Music stored in the Register.
 */
public class PieceOfMusic {

    private final String name;
    private final int id;

    private final char number; // 0-7
    private final char letter; //a-z

    public PieceOfMusic(String name, int id, char number, char letter) {
        this.name = name;
        this.id = id;
        this.number = number;
        this.letter = letter;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public char getNumber() {
        return number;
    }

    public char getLetter() {
        return letter;
    }
}
