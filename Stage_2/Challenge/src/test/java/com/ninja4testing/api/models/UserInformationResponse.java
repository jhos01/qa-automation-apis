package com.ninja4testing.api.models;

import java.util.List;

public class UserInformationResponse {
    private String userId;
    private String username;
    private List<Book> books;

    public UserInformationResponse() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
