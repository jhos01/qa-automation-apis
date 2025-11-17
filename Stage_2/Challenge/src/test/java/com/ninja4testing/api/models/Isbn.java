package com.ninja4testing.api.models;

public class Isbn {
    private String Isbn;

    public Isbn() {
    }


    public Isbn(String isbn) {
        this.Isbn = isbn;
    }

    public String getIsbn() {
        return Isbn;
    }

    public void setIsbn(String isbn) {
        Isbn = isbn;
    }
}
