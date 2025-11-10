package com.qaxpert.restAssured;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
//revisar como se importo esta libreria:
import static io.restassured.RestAssured.*;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.hamcrest.Matchers.*;


public class NotesApiTests {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://practice.expandtesting.com/notes/api";
        RestAssured.config = RestAssured.config().encoderConfig(
                EncoderConfig.encoderConfig()
                        .defaultContentCharset("UTF-8")  // fuerza UTF-8
                        .encodeContentTypeAs("application/x-www-form-urlencoded", ContentType.URLENC)
        );
        // Para ver exactamente qué URL está llamando Rest Assured
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    }
    @Test
    public void RegisterUser(){
        // 🔧 Configurar correctamente el encoder para UTF-8
        RestAssured.config = RestAssured.config().encoderConfig(
                EncoderConfig.encoderConfig()
                        .defaultContentCharset("UTF-8")  // fuerza UTF-8
                        .encodeContentTypeAs("application/x-www-form-urlencoded", ContentType.URLENC)
        );

        given()
            .contentType(ContentType.URLENC)
            .accept("application/json")
            .formParam("name","Liana Lui Jhos")
            .formParam("email", "qa" + System.currentTimeMillis() + "@mail.com")
            .formParam("password","123456780")

        .when()
            .post("/users/register")
        .then()
            .log().all()
            .statusCode(201)
            .body("message",equalTo("User account created successfully"))
            .body("success",equalTo(true))
            .body("data.id",notNullValue())
            .body("data.name",notNullValue())
            .body("data.email",notNullValue());

    }
    @Test
    public void loginUser (){

        given()
                .contentType(ContentType.URLENC)
                .accept("application/json")
                .formParam("email","alana@email.com")
                .formParam("password","1266964646")
        .when()
                .post("/users/login")
        .then()
                .statusCode(200)
                .body("message",equalTo("Login successful"))
                .body("data.name",notNullValue())
                .body("data.email",notNullValue())
                .body("data.token",notNullValue());

    }

    @Test
    public void loginUserWithInvalidEmail(){
        given()
                .contentType(ContentType.URLENC)
                .accept("application/json")
                .formParam("email","invalide@email.com")
                .formParam("password","1266964646")
        .when()
                .post("/users/login")
        .then()
                .statusCode(401)
                .body("message",equalTo("Incorrect email address or password"))
                .body("success",equalTo(false));
    }

    @Test
    public void loginUserWithInvalidPassword(){
        given()
                .contentType(ContentType.URLENC)
                .accept("application/json")
                .formParam("email","alana@email.com")
                .formParam("password","1200000000000")
                .when()
                .post("/users/login")
                .then()
                .statusCode(401)
                .body("message",equalTo("Incorrect email address or password"))
                .body("success",equalTo(false));
    }


}

