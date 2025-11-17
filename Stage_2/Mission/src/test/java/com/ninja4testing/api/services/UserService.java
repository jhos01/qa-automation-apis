package com.ninja4testing.api.services;

import com.ninja4testing.api.config.Endpoints;
import com.ninja4testing.api.models.user.UserRequest;
import io.restassured.response.Response;
import com.ninja4testing.api.config.Config;
import static io.restassured.RestAssured.given;

public class UserService {
    public Response createUser(UserRequest body) {
        return given()
                .header("Authorization", "Bearer " + Config.TOKEN)
                .contentType("application/json")
                .body(body)
                .post(Endpoints.USERS);
    }

    public Response getUsers() {
        return given()
                .get(Endpoints.USERS);
    }

    public Response getUserById(int id) {
        return given()
                .pathParam("id", id)
                .get(Endpoints.USER_BY_ID);
    }

    public Response updateUserPut(int id, UserRequest body) {
        return given()
                .header("Authorization", "Bearer " + Config.TOKEN)
                .contentType("application/json")
                .pathParam("id", id)
                .body(body)
                .put(Endpoints.USER_BY_ID);
    }

    public Response updateUserPatch(int id, UserRequest body) {
        return given()
                .header("Authorization", "Bearer " + Config.TOKEN)
                .contentType("application/json")
                .pathParam("id", id)
                .body(body)
                .patch(Endpoints.USER_BY_ID);
    }

}
