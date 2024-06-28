package ApiTests;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.assertj.core.api.Assertions;
import org.example.api.Requests;
import org.example.models.CreateUser;
import org.junit.jupiter.api.Test;

public class CreateUserTest {
    @Test
    public void test() {
        CreateUser expectedCreatedUser = new CreateUser("morpheus", "leader");

        CreateUser actualCreatedUser = Requests
                .checkStatusCodePost("/users", 201,expectedCreatedUser)
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/CreateUser.json")
                )
                .extract()
                .as(CreateUser.class);

        Assertions.assertThat(actualCreatedUser).isEqualTo(expectedCreatedUser);
    }
}
