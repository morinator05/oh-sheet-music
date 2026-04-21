package com.github.morinator05.ohsheetmusic.service;

public class ExportOptions {

    private boolean separateCategories;
    private boolean printCategories;
    private boolean highlightNewBeginLetter;

    public ExportOptions(boolean separateCategories, boolean printCategories, boolean highlightNewBeginLetter) {
        this.separateCategories = separateCategories;
        this.printCategories = printCategories;
        this.highlightNewBeginLetter = highlightNewBeginLetter;
    }

    public void setSeparateCategories(boolean seperateCategories) {
        this.separateCategories = seperateCategories;
    }

    public void setPrintCategories(boolean printCategories) {
        this.printCategories = printCategories;
    }

    public void setHighlightNewBeginLetter(boolean highlightNewBeginLetter) {
        this.highlightNewBeginLetter = highlightNewBeginLetter;
    }

    public boolean isSeparateCategories() {
        return separateCategories;
    }

    public boolean isPrintCategories() {
        return printCategories;
    }

    public boolean isHighlightNewBeginLetter() {
        return highlightNewBeginLetter;
    }
}
