package com.github.morinator05.ohsheetmusic.model;

import java.util.Objects;

public class Position{

    private String number;
    private String letter;

    public Position(String number, String letter) {
        this.number = number;
        this.letter = letter;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        //TODO add limits
        this.number = number;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        //TODO check if is valid letter
        this.letter = letter;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(number, position.number) && Objects.equals(letter, position.letter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, letter);
    }
}
