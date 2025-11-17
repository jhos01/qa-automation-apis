package com.ninja4testing.api.models;

import org.hamcrest.core.Is;

import java.util.List;

public class AddBookAssignedResponse {
    private List<Isbn> books;

    public AddBookAssignedResponse() {
    }

    public AddBookAssignedResponse(List<Isbn> books) {
        this.books = books;
    }

    public List<Isbn> getBooks() {
        return books;
    }

    public void setBooks(List<Isbn> books) {
        this.books = books;
    }
}

