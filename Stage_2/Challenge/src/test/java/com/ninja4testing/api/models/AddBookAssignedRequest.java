package com.ninja4testing.api.models;

import java.util.List;

public class AddBookAssignedRequest {
    private String userId;
    private List<Isbn> collectionOfIsbns;

    public AddBookAssignedRequest() {
    }

    public AddBookAssignedRequest(String userId, List<Isbn> collectionOfIsbns) {
        this.userId = userId;
        this.collectionOfIsbns = collectionOfIsbns;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Isbn> getCollectionOfIsbns() {
        return collectionOfIsbns;
    }

    public void setCollectionOfIsbns(List<Isbn> collectionOfIsbns) {
        this.collectionOfIsbns = collectionOfIsbns;
    }
}
