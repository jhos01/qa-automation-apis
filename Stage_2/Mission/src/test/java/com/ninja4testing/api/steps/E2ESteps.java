package com.ninja4testing.api.steps;
import com.ninja4testing.api.models.comment.CommentRequest;
import com.ninja4testing.api.models.post.PostRequest;
import com.ninja4testing.api.models.user.UserRequest;
import com.ninja4testing.api.services.CommentService;
import com.ninja4testing.api.services.PostService;
import com.ninja4testing.api.services.UserService;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static org.junit.Assert.*;

public class E2ESteps {
    UserService userService = new UserService();
    PostService postService = new PostService();
    CommentService commentService = new CommentService();

    Response response;

    int userId;
    int postId;
    int commentId;

    // ---------- 1. Crear usuario ----------
    @Given("envio una solicitud para crear un usuario con datos válidos para el flujo e2e")
    public void crear_usuario_e2e() {

        UserRequest user = new UserRequest(
                "Usuario e2e",
                "male",
                "e2e_user_" + System.currentTimeMillis() + "@mail.com",
                "active"
        );

        Response createUser = userService.createUser(user);
        assertEquals(201, createUser.statusCode());

        userId = createUser.jsonPath().getInt("id");
        assertTrue("No se creó el usuario", userId > 0);
    }

    // ---------- 2. Crear publicación ----------
    @Given("envio una solicitud para crear una publicación asociada al usuario para el flujo e2e")
    public void crear_publicacion_e2e() {

        PostRequest post = new PostRequest(
                userId,
                "Publicación e2e",
                "Contenido de publicación en flujo e2e"
        );

        Response createPost = postService.createPost(post);
        assertEquals(201, createPost.statusCode());

        postId = createPost.jsonPath().getInt("id");
        assertTrue("No se creó la publicación", postId > 0);
    }

    // ---------- 3. Crear comentario ----------
    @Given("envio una solicitud para crear un comentario asociado a la publicación para el flujo e2e")
    public void crear_comentario_e2e() {

        CommentRequest comment = new CommentRequest(
                postId,
                "Autor Flujo E2E",
                "e2e_comment_" + System.currentTimeMillis() + "@mail.com",
                "Comentario creado desde el flujo end-to-end"
        );

        Response createComment = commentService.createComment(comment);
        assertEquals(201, createComment.statusCode());

        commentId = createComment.jsonPath().getInt("id");
        assertTrue("No se creó el comentario", commentId > 0);
    }

    // ---------- 4. GET usuario ----------
    @When("consulto el usuario por su ID en el flujo e2e")
    public void consultar_usuario_e2e() {
        response = userService.getUserById(userId);
    }

    @Then("la respuesta del flujo e2e debe tener un status {int}")
    public void validar_status_e2e(Integer status) {
        assertEquals(status.intValue(), response.statusCode());
    }

    // ---------- 5. GET publicación ----------
    @When("consulto la publicación por su ID en el flujo e2e")
    public void consultar_publicacion_e2e() {
        response = postService.getPostById(postId);
    }

    // ---------- 6. GET comentarios por publicación ----------
    @When("consulto los comentarios de la publicación en el flujo e2e")
    public void consultar_comentarios_por_publicacion_e2e() {
        response = commentService.getCommentsByPostId(postId);
    }

    // ---------- 7. GET comentarios por usuario ----------
    @When("consulto los comentarios realizados por el usuario en el flujo e2e")
    public void consultar_comentarios_por_usuario_e2e() {
        response = commentService.getCommentsByUserId(userId);
    }
}
