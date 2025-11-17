package com.ninja4testing.api.steps;

import com.ninja4testing.api.models.user.UserRequest;
import com.ninja4testing.api.services.UserService;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import com.ninja4testing.api.config.Endpoints;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;


public class UserSteps {
    UserService userService = new UserService();
    Response response;
    int userId;


    // ---------- CREATE USER ----------
    @Given("envio una solicitud para crear un usuario con datos válidos")
    public void envio_una_solicitud_para_crear_un_usuario_con_datos_validos() {

        UserRequest body = new UserRequest(
                "Juan Perez",
                "male",
                "juan" + System.currentTimeMillis() + "@mail.com",
                "active"
        );

        response = userService.createUser(body);

        if(response.statusCode() == 201) {
            userId = response.jsonPath().getInt("id");
        }
    }
    // ---------- CREATE USER WITH SAME EMAIL ----------
    @When("envio una solicitud para crear un usuario con el mismo email")
    public void crear_usuario_con_email_repetido() {

        // Crear usuario base
        UserRequest user = new UserRequest(
                "Nombre Base",
                "male",
                "base_email_" + System.currentTimeMillis() + "@mail.com",
                "active"
        );

        Response r = userService.createUser(user);
        String duplicatedEmail = r.jsonPath().getString("email");

        // Intentar crearlo nuevamente
        UserRequest userDuplicado = new UserRequest(
                "Usuario Duplicado",
                "male",
                duplicatedEmail,
                "active"
        );

        response = userService.createUser(userDuplicado);
    }

    // ---------- CREATE USER WITHOUT EMAIL ----------
    @When("envio una solicitud para crear un usuario sin email")
    public void crear_usuario_sin_email() {
        UserRequest body = new UserRequest(
                "Usuario Sin Email",
                "male",
                null,
                "active"
        );
        response = userService.createUser(body);
    }

    // ---------- CREATE USER WITHOUT NAME ----------
    @When("envio una solicitud para crear un usuario sin nombre")
    public void crear_usuario_sin_nombre() {
        UserRequest body = new UserRequest(
                null,
                "male",
                "no_name_" + System.currentTimeMillis() + "@mail.com",
                "active"
        );
        response = userService.createUser(body);
    }

    // ---------- CREATE USER WITHOUT TOKEN ----------
    @When("envio una solicitud sin token para crear un usuario")
    public void crear_usuario_sin_token() {

        UserRequest body = new UserRequest(
                "Usuario Sin Token",
                "male",
                "sin_token_" + System.currentTimeMillis() + "@mail.com",
                "active"
        );

        response = given().log().all()
                .contentType("application/json")
                .body(body)
                .post(Endpoints.USERS);
    }

    // ---------- CREATE USER WITH INVALID TOKEN ----------
    @When("envio una solicitud con token inválido para crear un usuario")
    public void crear_usuario_con_token_invalido() {

        UserRequest body = new UserRequest(
                "Usuario Token Invalido",
                "male",
                "token_inval_" + System.currentTimeMillis() + "@mail.com",
                "active"
        );

        response = given().log().all()
                .header("Authorization", "Bearer TOKEN_INVALIDO123")
                .contentType("application/json")
                .body(body)
                .post(Endpoints.USERS);
    }

    // ---------- CREATE USER WITH INVALID GENDER ----------
    @When("envio una solicitud para crear un usuario con gender invalido")
    public void crear_usuario_con_gender_invalido() {

        UserRequest body = new UserRequest(
                "Usuario Gender BAD",
                "otro", // inválido
                "gender_bad_" + System.currentTimeMillis() + "@mail.com",
                "active"
        );

        response = userService.createUser(body);
    }

    // ---------- CREATE USER WITH DYNAMIC EMAIL ----------
    @When("envio una solicitud para crear un usuario con email dinámico")
    public void crear_usuario_email_dinamico() {

        String email = "dynamic_" + System.currentTimeMillis() + "@mail.com";

        UserRequest body = new UserRequest(
                "Usuario Dinamico",
                "female",
                email,
                "active"
        );

        response = userService.createUser(body);
    }


    // ---------- GET USERS ----------

    @When("envio una solicitud para obtener la lista de usuarios")
    public void envio_una_solicitud_para_obtener_la_lista_de_usuarios() {
        response = userService.getUsers();
    }

    @Then("la lista de usuarios debe contener registros")
    public void la_lista_de_usuarios_debe_contener_registros() {
        assertTrue(response.jsonPath().getList("$").size() > 0);
    }

    @Then("la respuesta debe ser una lista")
    public void validar_que_respuesta_es_lista() {
        assertTrue(response.jsonPath().getList("$") instanceof java.util.List);
    }

    @Then("los usuarios deben incluir los campos obligatorios")
    public void validar_campos_obligatorios_en_usuarios() {
        var list = response.jsonPath().getList("$");

        assertTrue(list.size() > 0);
        assertNotNull(response.jsonPath().getString("[0].id"));
        assertNotNull(response.jsonPath().getString("[0].name"));
        assertNotNull(response.jsonPath().getString("[0].email"));
    }

    @When("envio una solicitud para obtener usuarios con query invalida")
    public void obtener_usuarios_con_query_invalida() {
        response = given().log().all()
                .queryParam("page", "invalida")
                .get(Endpoints.USERS);
    }



    // ---------- GET USER BY ID ----------
    @Given("que existe un usuario registrado")
    public void que_existe_un_usuario_registrado() {

        UserRequest body = new UserRequest(
                "Carlos Sánchez",
                "male",
                "carlos" + System.currentTimeMillis() + "@mail.com",
                "active"
        );

        Response createResponse = userService.createUser(body);
        userId = createResponse.jsonPath().getInt("id");
        assertNotNull(userId,"El usuario no se creó correctamente (id nulo)");
        assertEquals(201,createResponse.getStatusCode());
    }

    // ---------- USER WITH ID THAT DOESN'T EXIST ----------
    @When("envio una solicitud para obtener el usuario con ID inexistente")
    public void obtener_usuario_id_inexistente() {
        response = userService.getUserById(999999999);
    }


    // ---------- USER WITH INVALID ID ----------
    @When("envio una solicitud para obtener el usuario con ID inválido")
    public void obtener_usuario_id_invalido() {
        response = given().log().all()
                .get(Endpoints.USERS + "/abc");
    }


    @When("envio una solicitud para obtener el usuario por su ID")
    public void envio_una_solicitud_para_obtener_el_usuario_por_su_id() {
        response = userService.getUserById(userId);
    }


    @Then("los datos del usuario deben ser correctos")
    public void los_datos_del_usuario_deben_ser_correctos() {
        assertEquals(userId, (int) response.jsonPath().getInt("id"));
    }

    // ---------- VALIDATE EMAIL IS CORRECT ----------
    @Then("el email del usuario debe ser correcto")
    public void validar_email_de_usuario() {
        String email = response.jsonPath().getString("email");
        assertTrue(email.contains("@"));
    }




    // ---------- UPDATE USER (PUT) ----------
    @When("envio una solicitud PUT para actualizar todos los datos del usuario")
    public void envio_una_solicitud_put_para_actualizar_todos_los_datos_del_usuario() {

        UserRequest body = new UserRequest(
                "Nombre Actualizado PUT",
                "female",
                "updated_put_" + System.currentTimeMillis() + "@mail.com",
                "inactive"
        );

        response = userService.updateUserPut(userId, body);
    }
    // ----------PUT REQUEST SIN TOKEN ----------
    @When("envio una solicitud PUT sin token para actualizar el usuario")
    public void actualizar_usuario_put_sin_token() {

        UserRequest body = new UserRequest(
                "Nuevo Nombre",
                "female",
                "put_no_token_" + System.currentTimeMillis() + "@mail.com",
                "inactive"
        );

        response = given().log().all()
                .contentType("application/json")
                .pathParam("id", userId)
                .body(body)
                .put(Endpoints.USER_BY_ID);
    }


    @Then("los datos del usuario deben reflejar los cambios")
    public void los_datos_del_usuario_deben_reflejar_los_cambios() {
        assertEquals("Nombre Actualizado PUT", response.jsonPath().getString("name"));
    }

    // ---------- UPDATE USER (PATCH) ----------
    @When("envio una solicitud PATCH para modificar parcialmente los datos del usuario")
    public void envio_una_solicitud_patch_para_modificar_parcialmente_los_datos_del_usuario() {

        UserRequest body = new UserRequest(
                "Nombre Parcial PATCH",
                null,   // No se modifican
                null,   // No se modifican
                null    // No se modifican
        );

        response = userService.updateUserPatch(userId, body);
    }

    // ---------- UPDATE USER (PATCH) WITH INVALID EMAIL----------
    @When("envio una solicitud PATCH para actualizar el email invalido")
    public void patch_email_invalido() {

        UserRequest body = new UserRequest(
                null,
                null,
                "email-invalido",   // email SIN @ → invalido
                null
        );

        response = userService.updateUserPatch(userId, body);
    }


    @Then("los datos actualizados deben reflejar los cambios parciales")
    public void los_datos_actualizados_deben_reflejar_los_cambios_parciales() {
        assertEquals("Nombre Parcial PATCH", response.jsonPath().getString("name"));
    }


    // ---------- VALIDACIÓN GENERAL ----------
    @Then("la respuesta debe tener un status {int}")
    public void la_respuesta_debe_tener_un_status(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.statusCode());
    }



}