package ApiTests;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.assertj.core.api.Assertions;
import org.example.api.Requests;
import org.example.models.AuthSchema;
import org.example.models.Register;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class RegisterSuccessfulTest {
    @Test
    public void test() {
        AuthSchema registerObject = new AuthSchema("eve.holt@reqres.in", "pistol");

        Register registerResponse = Requests
                .checkStatusCodePost("/register", 200, registerObject)
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/Register.json"))
                .extract()
                .as(Register.class);

        Assertions
                .assertThat(registerResponse)
                .extracting("id", "token")
                .allMatch(Objects::nonNull);
    }
}
