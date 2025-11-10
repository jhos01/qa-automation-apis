package com.qaxpert.restassured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class TheSimpsonsApiTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://thesimpsonsapi.com/api";
    }

    // CP01 - passed
    @Test
    public void CP01_userCanListCharactersByPageSuccessfully() {
        given()
                .queryParam("page", 1)
                .when()
                .get("/characters")
                .then()
                .statusCode(200);
    }

    // CP02 - passed
    @Test
    public void CP02_userSees20CharactersOnPage1() {
        given()
                .queryParam("page", 1)
                .when()
                .get("/characters")
                .then()
                .statusCode(200)
                .body("results.size()", equalTo(20));
    }

    // CP03 - passed
    @Test
    public void CP03_userSees20CharactersOnAPage() {
        given()
                .queryParam("page", 10)
                .when()
                .get("/characters")
                .then()
                .statusCode(200)
                .body("results.size()", equalTo(20));
    }

    // CP04 - passed
    @Test
    public void CP04_responseContainsPaginationMetadata() {
        given()
                .queryParam("page", 2)
                .when()
                .get("/characters")
                .then()
                .statusCode(200)
                .body("$", hasKey("count"))
                .body("$", hasKey("next"))
                .body("$", hasKey("prev"))
                .body("$", hasKey("pages"))
                .body("$", hasKey("results"));
    }

    // CP05 - the api is retrieving wrong code response
    @Test
    public void CP05_invalidPageParameter_abc_returns400() {
        given()
                .queryParam("page", "abc")
                .when()
                .get("/characters")
                .then()
                .statusCode(400);
    }

    // CP06 - the returns 200 for invalid page number = 0
    @Test
    public void CP06_invalidPageParameter_0_returns400() {
        given()
                .queryParam("page", 0)
                .when()
                .get("/characters")
                .then()
                .statusCode(400);
    }

    // CP07 - passed
    @Test
    public void CP07_SendApiWithoutPageValue() {
        List<Integer> ids =
                given()
                        .when()
                        .get("/characters")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("results.id");

        List<Integer> expected = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());
        assertEquals(expected, ids, "La respuesta no contiene los IDs 1-20");
    }

    // CP08 - passed
    @Test
    public void CP08_ReceiveCount1182() {
        given()
                .when()
                .get("/characters")
                .then()
                .statusCode(200)
                .body("count", equalTo(1182));
    }

    // CP09 - passed
    @Test
    public void CP09_ValidateNextFieldReturnsProperPageNumber() {
        given()
                .queryParam("page", 6)
                .when()
                .get("/characters")
                .then()
                .statusCode(200)
                .body("next", equalTo("https://thesimpsonsapi.com/api/characters?page=7"));
    }

    // CP10 - passed
    @Test
    public void CP10_UserCanGetCharacterByIdSuccessfully() {
        given()
                .pathParam("id", 5)
                .when()
                .get("/characters/{id}")
                .then()
                .statusCode(200);
    }

    // CP11 - passed
    @Test
    public void CP11_ValidateCharacterByIdContainsRequiredFields() {
        given()
                .pathParam("id", 1)
                .when()
                .get("/characters/{id}")
                .then()
                .statusCode(200)
                .body("$", hasKey("id"))
                .body("$", hasKey("age"))
                .body("$", hasKey("birthdate"))
                .body("$", hasKey("gender"))
                .body("$", hasKey("name"))
                .body("$", hasKey("occupation"))
                .body("$", hasKey("portrait_path"))
                .body("$", hasKey("phrases"))
                .body("$", hasKey("status"));
    }

    // CP12 - Failed (some characters have null value)
    @Test
    public void CP12_ValidatebirthdateHasValidFormat() {
        String birthdate =
                given()
                    .pathParam("id", 2)
                .when()
                    .get("/characters/{id}")
                .then()
                    .statusCode(200)
                    .extract()
                    .path("birthdate");
        assertNotNull(birthdate, "Birthdate field is null for this character");
        assertTrue(birthdate.matches("\\d{4}-\\d{2}-\\d{2}"), "Formato de birthdate inválido: " + birthdate);
    }

    // CP13 - passed
    @Test
    public void CP13_ValidatePortraitPathExists() {
        given()
                .pathParam("id", 3)
                .when()
                .get("/characters/{id}")
                .then()
                .statusCode(200)
                .body("portrait_path", notNullValue());
    }

    // CP14 -passed
    @Test
    public void CP14_ValidatePhrasesIsNotNullOrEmpty() {
        List<String> phrases =
                given()
                        .pathParam("id", 4)
                        .when()
                        .get("/characters/{id}")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("phrases");

        assertNotNull(phrases, "phrases es null");
    }

    // CP15 - passed
    @Test
    public void CP15_characterId1IsHomerSimpson() {
        given()
                .pathParam("id", 1)
                .when()
                .get("/characters/{id}")
                .then()
                .statusCode(200)
                .body("name", equalTo("Homer Simpson"));
    }

    // CP16 - passed
    @Test
    public void CP16_ApiFailsForIdsNotExist() {
        given()
                .pathParam("id", 0)
                .when()
                .get("/characters/{id}")
                .then()
                .statusCode(404);
    }

    // CP17 -passed
    @Test
    public void CP17_invalidIdFormatReturns400() {
        given()
                .pathParam("id", "abc")
                .when()
                .get("/characters/{id}")
                .then()
                .statusCode(400);
    }

    // CP18 - passed
    @Test
    public void CP18_ResponseReturnsIdsInAscendingOrder() {
        List<Integer> ids =
                given()
                        .queryParam("page", 6)
                        .when()
                        .get("/characters")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("results.id");

        List<Integer> sorted = new ArrayList<>(ids);
        Collections.sort(sorted);
        assertEquals(sorted, ids, "Los IDs no están en orden ascendente");
    }

    // CP19 - passed
    @Test
    public void CP19_character19IsGaryChalmers() {
        given()
                .pathParam("id", 19)
                .when()
                .get("/characters/{id}")
                .then()
                .statusCode(200)
                .body("name", equalTo("Gary Chalmers"));
    }

    // CP20 - passed
    @Test
    public void CP20_character14IsSmithers() {
        given()
                .pathParam("id", 14)
                .when()
                .get("/characters/{id}")
                .then()
                .statusCode(200)
                .body("name", equalTo("Waylon Smithers, Jr."));
    }
}



