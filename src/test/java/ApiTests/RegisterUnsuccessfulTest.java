package ApiTests;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.assertj.core.api.Assertions;
import org.example.api.Requests;
import org.example.models.CreateUser;
import org.example.models.Error;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class RegisterUnsuccessfulTest {
    @Test
    public void test() {
        Error error = Requests
                .checkStatusCodePost("/register", 400, Map.of("email", "sydney@fife"))
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/Error.json")
                )
                .extract()
                .as(Error.class);

        Assertions.assertThat(error.getError()).isEqualTo("Missing password");
    }
}
