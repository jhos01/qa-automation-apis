package com.ninja4testing.api.steps;

import com.ninja4testing.api.config.Config;
import com.ninja4testing.api.config.Endpoints;
import com.ninja4testing.api.models.AddBookAssignedRequest;
import com.ninja4testing.api.models.AddBookAssignedResponse;
import com.ninja4testing.api.models.BookResponse;
import com.ninja4testing.api.models.Isbn;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AssignFirstBookToUserSteps {

    private String userID;
    private String user_token;
    private String firstIsbn;
    private Response response;

    @Given("que tengo 1 userID valido {string}")
    public void guardar_userID(String userID){
        this.userID = userID;

    }

    @And("que tengo un token valido {string}")
    public void guardar_token_valido(String user_token){
        this.user_token = user_token;
    }

    @When("Obtengo la lista de libros disponibles")
    public void obtener_lista_libros_disponibles(){
        response = given().log().all()
                .baseUri(Config.BASE_URL)
                .when()
                .get(Endpoints.GET_AVAILABLE_BOOKS)
                .then()
                .extract()
                .response();
        assertEquals(200,response.getStatusCode(), "La API debe responder 200");
    }


    @And("Obtengo el ISBN del primer libro")
    public void obtener_isbn_del_primer_libro(){
        BookResponse bookresponse = response.as(BookResponse.class);
        firstIsbn = bookresponse.getBooks().get(0).getIsbn();
        assertNotNull(firstIsbn,"El primer isbn n debe ser null");
    }
    @And("asigno ese libro al usuario")
    public void asignar_libro_al_usuario(){
        List<Isbn> listaIsbn = new ArrayList<>();
        listaIsbn.add(new Isbn(firstIsbn));

        AddBookAssignedRequest request = new AddBookAssignedRequest(userID,listaIsbn);

        response = given().log().all()
                . baseUri(Config.BASE_URL)
                .header("Authorization", "Bearer " + user_token)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(Endpoints.ADD_ONE_BOOK_TO_USER)
                .then()
                .extract()
                .response();
    }

    @Then("la respuesta debe tener status 201")
    public void _validar_status_code(){
        assertEquals(201,response.getStatusCode(),"El codigo esperado es 201");
        AddBookAssignedResponse request2 = response.as(AddBookAssignedResponse.class);
    }



}
