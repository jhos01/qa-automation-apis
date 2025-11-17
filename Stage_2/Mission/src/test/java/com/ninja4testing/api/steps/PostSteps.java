package com.ninja4testing.api.steps;
import com.ninja4testing.api.config.Endpoints;
import com.ninja4testing.api.models.post.PostRequest;
import com.ninja4testing.api.models.user.UserRequest;
import com.ninja4testing.api.services.PostService;
import com.ninja4testing.api.services.UserService;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class PostSteps {
    UserService userService = new UserService();
    PostService postService = new PostService();

    Response response;
    Integer user_id;
    Integer postId;

    // ----------- PRECONDICIÓN: Usuario existente -----------
    @Given("que existe un usuario registrado para publicaciones")
    public void crear_usuario_para_publicaciones() {

        UserRequest user = new UserRequest(
                "Usuario Publicaciones",
                "male",
                "user_pub_" + System.currentTimeMillis() + "@mail.com",
                "active"
        );

        Response createUser = userService.createUser(user);
        assertEquals(201, createUser.statusCode());

        user_id = createUser.jsonPath().getInt("id");
        assertNotNull(user_id,"El usuario no se creó correctamente");
    }

    // ----------- CREAR PUBLICACIÓN -----------
    @When("envio una solicitud para crear una publicación para ese usuario")
    public void crear_publicacion_para_usuario() {

        PostRequest body = new PostRequest(
                user_id,
                "Titulo de publicación",
                "Este es el contenido del post"
        );

        response = postService.createPost(body);

        if (response.statusCode() == 201) {
            postId = response.jsonPath().getInt("id");
        }
    }

    // ----------- CREATE POST without User_id -----------
    @When("envio una solicitud para crear una publicación sin user_id")
    public void crear_publicacion_sin_user_id() {

        PostRequest body = new PostRequest(
                null,
                "Titulo sin user_id",
                "Body sin user_id"
        );

        response = postService.createPost(body);
    }

    // ----------- CREATE POST with User_id that doesn't exist -----------
    @When("envio una solicitud para crear una publicación con user_id inexistente")
    public void crear_publicacion_user_inexistente() {

        PostRequest body = new PostRequest(
                9999999,
                "Titulo user inexistente",
                "Body user inexistente"
        );

        response = postService.createPost(body);
    }
    // ----------- CREATE POST without Title -----------
    @When("envio una solicitud para crear una publicación sin titulo")
    public void crear_post_sin_titulo() {

        PostRequest body = new PostRequest(
                user_id,
                null,
                "Body sin titulo"
        );

        response = postService.createPost(body);
    }

    // ----------- CREATE POST without Body -----------
    @When("envio una solicitud para crear una publicación sin body")
    public void crear_post_sin_body() {

        PostRequest body = new PostRequest(
                user_id,
                "Titulo sin body",
                null
        );

        response = postService.createPost(body);
    }


    // ----------- CREATE POST with empty title -----------
    @When("envio una solicitud para crear una publicación con titulo vacio")
    public void crear_post_titulo_vacio() {

        PostRequest body = new PostRequest(
                user_id,
                "",
                "Body con titulo vacío"
        );

        response = postService.createPost(body);
    }

    // ----------- CREATE POST without Token -----------
    @When("envio una solicitud sin token para crear una publicación")
    public void crear_post_sin_token() {

        PostRequest body = new PostRequest(
                user_id,
                "Titulo sin token",
                "Body sin token"
        );

        response = given()
                .contentType("application/json")
                .body(body)
                .post(Endpoints.POSTS);
    }

    // ----------- CREATE POST with invalid Token -----------
    @When("envio una solicitud con token inválido para crear una publicación")
    public void crear_post_token_invalido() {

        PostRequest body = new PostRequest(
                user_id,
                "Titulo token inválido",
                "Body token inválido"
        );

        response = given()
                .header("Authorization", "Bearer TOKEN_MALO123")
                .contentType("application/json")
                .body(body)
                .post(Endpoints.POSTS);
    }

    // ----------- CREAR POST con Body muy largo -----------
    @When("envio una solicitud con body largo para crear una publicación")
    public void crear_post_body_largo() {

        String largo = "A".repeat(600);

        PostRequest body = new PostRequest(
                user_id,
                "Titulo largo",
                largo
        );

        response = postService.createPost(body);
    }

    // ----------- CREAR POST con datos dinamicos -----------
    @When("envio una solicitud para crear una publicación con datos dinámicos")
    public void crear_post_dinamico() {

        PostRequest body = new PostRequest(
                user_id,
                "Titulo dinámico " + System.currentTimeMillis(),
                "Body dinámico " + System.currentTimeMillis()
        );

        response = postService.createPost(body);
    }

    // ----------- CREAR POST con userId como string -----------
    @When("envio una solicitud para crear una publicación con user_id string")
    public void crear_post_user_string() {
        response = given()
                .contentType("application/json")
                .body("{\"user_id\":\"abc\",\"title\":\"Titulo\",\"body\":\"Contenido\"}")
                .post(Endpoints.POSTS);
    }

    @Then("la publicación debe contener un id")
    public void la_publicacion_debe_contener_un_id() {
        assertNotNull(postId,"La publicación no devolvió un ID");
    }

    // ----------- OBTENER LISTA DE PUBLICACIONES -----------
    @When("envio una solicitud para obtener todas las publicaciones")
    public void obtener_todas_las_publicaciones() {
        response = postService.getPosts();
    }

    @Then("la lista de publicaciones debe contener registros")
    public void validar_lista_publicaciones() {
        assertTrue(response.jsonPath().getList("$").size() > 0);
    }

    // ----------- PRECONDICIÓN: Publicación creada -----------
    @Given("que existe una publicación creada para ese usuario")
    public void crear_publicacion_precondicion() {

        PostRequest body = new PostRequest(
                user_id,
                "Título inicial",
                "Contenido inicial"
        );

        Response createPostResponse = postService.createPost(body);
        assertEquals(201, createPostResponse.statusCode());

        postId = createPostResponse.jsonPath().getInt("id");
        assertNotNull(postId,"No se creó la publicación");
    }

    // ----------- OBTENER PUBLICACIÓN POR ID -----------
    @When("envio una solicitud para obtener la publicación por su ID")
    public void obtener_publicacion_por_id() {
        response = postService.getPostById(postId);
    }

    @Then("los datos de la publicación deben ser correctos")
    public void validar_datos_publicacion() {
        assertEquals(postId, (int) response.jsonPath().getInt("id"));
        assertEquals(user_id, (int) response.jsonPath().getInt("user_id"));
    }

    // ----------- VALIDACIÓN GENERAL -----------
    @Then("la respuesta de publicaciones debe tener un status {int}")
    public void validar_status_publicaciones(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.statusCode());
    }

    // ----------- GET POSTS WITHOUT TOKEN -----------
    @When("envio una solicitud para obtener todas las publicaciones sin token")
    public void obtener_posts_sin_token() {
        response = given().get(Endpoints.POSTS);
    }

    // ----------- GET POSTS WITH INVALID QUERY -----------
    @When("envio una solicitud para obtener publicaciones con query invalida")
    public void obtener_posts_query_invalida() {
        response = given()
                .queryParam("page", "abc")
                .get(Endpoints.POSTS);
    }

    // ----------- VALIDATE MANDATORY FIELDS -----------
    @Then("todas las publicaciones deben tener los campos obligatorios")
    public void validar_campos_obligatorios_posts() {
        assertNotNull(response.jsonPath().getString("[0].id"));
        assertNotNull(response.jsonPath().getString("[0].user_id"));
        assertNotNull(response.jsonPath().getString("[0].title"));
    }


    // ----------- GET POST USING AN ID THAT DOESN'T EXIST -----------
    @When("envio una solicitud para obtener la publicación con ID inexistente")
    public void obtener_post_inexistente() {
        response = postService.getPostById(999999999);
    }

    // ----------- GET POST USING AN INVALID ID -----------
    @When("envio una solicitud para obtener la publicación con ID inválido")
    public void obtener_post_invalido() {
        response = given().get("/posts/abc");
    }

    // ----------- GET POST FROM OTHER USER -----------
    @When("envio una solicitud para obtener una publicación de otro usuario")
    public void obtener_publicacion_otro_usuario() {
        response = postService.getPostById(100);  // cualquier post público
    }


}
