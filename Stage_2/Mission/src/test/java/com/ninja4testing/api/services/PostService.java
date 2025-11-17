package com.ninja4testing.api.services;
import com.ninja4testing.api.config.Config;
import com.ninja4testing.api.config.Endpoints;
import com.ninja4testing.api.models.post.PostRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostService {
    public Response createPost(PostRequest body) {
        return given()
                .header("Authorization", "Bearer " + Config.TOKEN)
                .contentType("application/json")
                .body(body)
                .post(Endpoints.POSTS);
    }

    public Response getPosts() {
        return given()
                .get(Endpoints.POSTS);
    }

    public Response getPostById(int id) {
        return given()
                .pathParam("id", id)
                .get(Endpoints.POST_BY_ID);
    }
}
