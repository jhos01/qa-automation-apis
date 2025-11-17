package com.ninja4testing.api.steps;

import com.ninja4testing.api.config.Config;
import com.ninja4testing.api.config.Endpoints;
import com.ninja4testing.api.models.Book;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReceiveUserInformationwithNoTokenSteps {
    private String name;
    private String salary;
    private String age;
    private String userId;
    private String username;
    private List<Book> books;
    private String invalidToken;
    private Response response;


    @Given("Dado un UserID valido {string}")
    public void guardar_userId(String userId){
        this.userId = userId;
    }
    @Given("que preparo un token invalido {string}")
    public void prepar_token_invalido(String invalidToken){
        this.invalidToken = invalidToken;
    }

    @When("El usuario envia la peticion con token invalido")
    public void enviar_peticion_sin_token(){
        response = given().log().all()
                .baseUri(Config.BASE_URL)
                .header("Authorization","Bearer " + invalidToken)
                .when()
                .get(Endpoints.GET_USER_INFORMATION  + userId)
                .then()
                .extract()
                .response();
    }

    @Then("La respuesta debe devolver status 401")
    public void validar_status(){
        assertEquals(401,response.getStatusCode());
    }

    @Then("el mensaje de error debe ser {string}")
    public void validar_mensaje_error(String expectedMessage){
        String actualMessage = response.jsonPath().getString("message");
        assertEquals(expectedMessage, actualMessage,"El mensaje de error no coincide");
    }



}
