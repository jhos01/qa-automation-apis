import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

public class FirstApiTest {

    @Before
    public void setup() {
        // 1. Configuración centralizada
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void getSingleUserTest() {
        // 3. Variables para datos clave
        int userId = 2;
        String expectedUserName = "Janet";

        // WHEN: Envío la petición
        RestAssured
                .when()
                .get("/users/" + userId)
                // THEN: Valido la respuesta
                .then()
                .log().all()
                // 2. Aserciones más legibles
                .statusCode(200)
                .body("data.id", equalTo(userId))
                .body("data.first_name", equalTo(expectedUserName));
    }
}