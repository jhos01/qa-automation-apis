package com.ninja4testing.api.config;

import java.util.concurrent.PriorityBlockingQueue;

public class Endpoints {
    // USERS
    public static final String USERS = "/users";
    public static final String USER_BY_ID = "/users/{id}";

    // POSTS
    public static final String POSTS = "/posts";
    public static final String POST_BY_ID = "/posts/{id}";

    // COMMENTS
    public static final String COMMENTS = "/comments";
    public static final String COMMENTS_BY_POST = "/posts/{id}/comments";
    public static final String COMMENTS_BY_USER = "/comments?user_id={userId}";

}

