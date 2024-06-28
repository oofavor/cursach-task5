package ApiTests;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.assertj.core.api.Assertions;
import org.example.api.Requests;
import org.example.models.AuthSchema;
import org.example.models.Login;
import org.junit.jupiter.api.Test;

public class LoginSuccessful {
    @Test
    public void test() {
        AuthSchema loginData = new AuthSchema("eve.holt@reqres.in", "cityslicka");

        Login loginResponse = Requests
                .checkStatusCodePost("/login", 200, loginData)
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/Login.json"))
                .extract()
                .as(Login.class);

        Assertions.assertThat(loginResponse.getToken()).isNotNull();
    }
}
