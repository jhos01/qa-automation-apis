package com.ninja4testing.api.utils;

import com.github.javafaker.Faker;
import com.ninja4testing.api.models.PostRequest;

public class DataFactory {

    private static final Faker FAKER = new Faker();

    public static PostRequest randomPost(Integer userId) {
        String title = FAKER.book().title();
        String body  = FAKER.lorem().sentence(10);
        return new PostRequest(title, body, userId);
    }
}
