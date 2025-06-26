package com.example.librarycatalog.model.enumeration;

public enum ReadingStatus {
    TO_READ("Oxunacaq"),
    READING("Oxunur"),
    READ("Oxunmuş");

    private final String displayName;

    ReadingStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
