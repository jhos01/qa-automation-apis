package com.ninja4testing.api.services;

import com.ninja4testing.api.config.Endpoints;
import com.ninja4testing.api.models.comment.CommentRequest;
import io.restassured.response.Response;
import com.ninja4testing.api.config.Config;

import static io.restassured.RestAssured.given;

public class CommentService {
    public Response createComment(CommentRequest body) {
        return given()
                .header("Authorization", "Bearer " + Config.TOKEN)
                .contentType("application/json")
                .body(body)
                .post(Endpoints.COMMENTS);
    }

    public Response getCommentsByPostId(int postId) {
        return given()
                .pathParam("id", postId)
                .get(Endpoints.COMMENTS_BY_POST);
    }

    public Response getCommentsByUserId(int userId) {
        return given()
                .pathParam("userId", userId)
                .get(Endpoints.COMMENTS_BY_USER);
    }
}
