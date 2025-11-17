package com.ninja4testing.api.steps;
import com.ninja4testing.api.config.Endpoints;
import com.ninja4testing.api.models.comment.CommentRequest;
import com.ninja4testing.api.models.post.PostRequest;
import com.ninja4testing.api.models.user.UserRequest;
import com.ninja4testing.api.services.CommentService;
import com.ninja4testing.api.services.PostService;
import com.ninja4testing.api.services.UserService;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommentSteps {
    UserService userService = new UserService();
    PostService postService = new PostService();
    CommentService commentService = new CommentService();

    Response response;
    int userId;
    int postId;
    int commentId;

    // ----------- PRECONDICIÓN: Usuario -----------
    @Given("que existe un usuario registrado para comentarios")
    public void crear_usuario_para_comentarios() {

        UserRequest user = new UserRequest(
                "Usuario Comentarios",
                "male",
                "user_comment_" + System.currentTimeMillis() + "@mail.com",
                "active"
        );

        Response createUser = userService.createUser(user);
        assertEquals(201, createUser.statusCode());

        userId = createUser.jsonPath().getInt("id");
        assertTrue(userId > 0);
    }

    // ----------- PRECONDICIÓN: Publicación -----------
    @Given("que existe una publicación creada para ese usuario para comentarios")
    public void crear_publicacion_para_comentarios() {

        PostRequest post = new PostRequest(
                userId,
                "Título para comentarios",
                "Contenido para comentarios"
        );

        Response createPost = postService.createPost(post);
        assertEquals(201, createPost.statusCode());

        postId = createPost.jsonPath().getInt("id");
        assertTrue(postId > 0);
    }

    // ----------- CREAR COMENTARIO -----------
    @When("envio una solicitud para crear un comentario para esa publicación")
    public void crear_comentario_para_publicacion() {

        CommentRequest body = new CommentRequest(
                postId,
                "Autor del comentario",
                "autor" + System.currentTimeMillis() + "@mail.com",
                "Este es un comentario de prueba"
        );

        response = commentService.createComment(body);

        if (response.statusCode() == 201) {
            commentId = response.jsonPath().getInt("id");
        }
    }

    @Then("el comentario debe contener un id")
    public void validar_id_comentario() {
        assertTrue(commentId > 0,"El comentario no devolvió un id");
    }

    // ----------- PRECONDICIÓN: Comentario creado -----------
    @Given("que existe un comentario creado para esa publicación")
    public void crear_comentario_precondicion() {

        CommentRequest comment = new CommentRequest(
                postId,
                "Nombre comentario",
                "comment" + System.currentTimeMillis() + "@mail.com",
                "Contenido comentario inicial"
        );

        Response createComment = commentService.createComment(comment);
        assertEquals(201, createComment.statusCode());

        commentId = createComment.jsonPath().getInt("id");
        assertTrue(commentId > 0);
    }

    // ----------- OBTENER COMENTARIOS POR POST -----------
    @When("envio una solicitud para obtener los comentarios de esa publicación")
    public void obtener_comentarios_por_post() {
        response = commentService.getCommentsByPostId(postId);
    }

    @Then("la lista de comentarios debe contener registros")
    public void validar_lista_comentarios() {
        assertTrue(response.jsonPath().getList("$").size() > 0);
    }

    // ----------- OBTENER COMENTARIOS POR USER -----------
    @When("envio una solicitud para obtener los comentarios realizados por ese usuario")
    public void obtener_comentarios_por_usuario() {
        response = commentService.getCommentsByUserId(userId);
    }

    // ----------- VALIDACIÓN GENERAL -----------
    @Then("la respuesta de comentarios debe tener un status {int}")
    public void validar_status(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.statusCode());
    }

    // ----------- Crear comentario sin post-id -----------
    @When("envio una solicitud para crear un comentario sin post_id")
    public void comentario_sin_post_id() {

        CommentRequest body = new CommentRequest(
                null,
                "Autor",
                "autor@mail.com",
                "Comentario sin post_id"
        );

        response = commentService.createComment(body);
    }

    // ----------- Crear comentario con post-id inexistente -----------
    @When("envio una solicitud para crear un comentario con post_id inexistente")
    public void comentario_post_inexistente() {

        CommentRequest body = new CommentRequest(
                9999999,
                "Autor Post Inexistente",
                "autor@mail.com",
                "Comentario"
        );

        response = commentService.createComment(body);
    }

    // ----------- Crear comentario sin email -----------
    @When("envio una solicitud para crear un comentario sin email")
    public void comentario_sin_email() {

        CommentRequest body = new CommentRequest(
                postId,
                "Autor",
                null,
                "Comentario sin email"
        );

        response = commentService.createComment(body);
    }

    // ----------- Crear comentario con email invalido -----------
    @When("envio una solicitud para crear un comentario con email invalido")
    public void comentario_email_invalido() {

        CommentRequest body = new CommentRequest(
                postId,
                "Autor",
                "email-invalido",
                "Comentario con email invalido"
        );

        response = commentService.createComment(body);
    }

    // ----------- Crear comentario con body vacio -----------
    @When("envio una solicitud para crear un comentario con body vacio")
    public void comentario_body_vacio() {

        CommentRequest body = new CommentRequest(
                postId,
                "Autor",
                "autor@mail.com",
                ""
        );

        response = commentService.createComment(body);
    }

    // ----------- Crear comentario sin nombre -----------
    @When("envio una solicitud para crear un comentario sin nombre")
    public void comentario_sin_nombre() {

        CommentRequest body = new CommentRequest(
                postId,
                null,
                "autor@mail.com",
                "Comentario sin nombre"
        );

        response = commentService.createComment(body);
    }

    // ----------- Crear comentario sin token -----------
    @When("envio una solicitud sin token para crear un comentario")
    public void comentario_sin_token() {

        CommentRequest body = new CommentRequest(
                postId,
                "Autor",
                "autor@mail.com",
                "Sin token"
        );

        response = given()
                .contentType("application/json")
                .body(body)
                .post(Endpoints.COMMENTS);
    }

    // ----------- Crear comentario con token invalido -----------
    @When("envio una solicitud con token inválido para crear un comentario")
    public void comentario_token_invalido() {

        CommentRequest body = new CommentRequest(
                postId,
                "Autor",
                "autor@mail.com",
                "Token invalido"
        );

        response = given()
                .header("Authorization", "Bearer 123TOKENMALO")
                .contentType("application/json")
                .body(body)
                .post(Endpoints.COMMENTS);
    }

    // ----------- Crear comentario dinamico -----------

    @When("envio una solicitud para crear un comentario dinámico")
    public void comentario_dinamico() {

        CommentRequest body = new CommentRequest(
                postId,
                "Autor Dinamico",
                "dyn_" + System.currentTimeMillis() + "@mail.com",
                "Comentario dinamico"
        );

        response = commentService.createComment(body);
    }

    // ----------- Crear comentario con user_id incorrecto -----------

    @When("envio una solicitud para crear un comentario con user_id en body erroneo")
    public void comentario_userid_erroneo() {
        response = given()
                .contentType("application/json")
                .body("{\"user_id\": 5000, \"post_id\": " + postId + ", \"name\": \"Test\", \"email\": \"test@mail.com\", \"body\": \"body\"}")
                .post(Endpoints.COMMENTS);
    }

    // ----------- Obtener comentarios by post desde un post inexistente -----------
    @When("envio una solicitud para obtener comentarios de publicación inexistente")
    public void comentarios_post_inexistente() {
        response = commentService.getCommentsByPostId(9999999);
    }


    // ----------- Obtener comentarios by post desde un post invalido -----------
    @When("envio una solicitud para obtener comentarios de publicación con ID inválido")
    public void comentarios_post_invalido() {
        response = given().get("/posts/abc/comments");
    }

    // ----------- Validar campos obligatorios `del comentario -----------
    @Then("los comentarios deben tener campos obligatorios")
    public void validar_campos_obligatorios_comment() {
        assertNotNull(response.jsonPath().getString("[0].id"));
        assertNotNull(response.jsonPath().getString("[0].post_id"));
        assertNotNull(response.jsonPath().getString("[0].email"));
    }

    // ----------- User Id inexistente -----------
    @When("envio una solicitud para obtener comentarios con user_id inexistente")
    public void comentarios_user_inexistente() {
        response = commentService.getCommentsByUserId(9999999);
    }

    // ----------- User Id invalido -----------
    @When("envio una solicitud para obtener comentarios con user_id inválido")
    public void comentarios_user_invalido() {
        response = given().get("/comments?user_id=abc");
    }

    // ----------- Validar estructura del comentario por usuario -----------
    @Then("los comentarios por usuario deben tener campos obligatorios")
    public void validar_campos_obligatorios_comment_user() {
        assertNotNull(response.jsonPath().getString("[0].id"));
        assertNotNull(response.jsonPath().getString("[0].post_id"));
        assertNotNull(response.jsonPath().getString("[0].body"));
    }



}
