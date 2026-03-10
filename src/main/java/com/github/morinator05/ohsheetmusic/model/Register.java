package com.github.morinator05.ohsheetmusic.model;

import java.util.List;

public class Register {

    private List<PieceOfMusic> contents;

    Register() {

    }

    public void addComponent(PieceOfMusic newComponent) {
        contents.add(newComponent);
    }

    public List<PieceOfMusic> getContents() {
        return contents;
    }
}
