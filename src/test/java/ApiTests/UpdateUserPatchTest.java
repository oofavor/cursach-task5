package ApiTests;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.assertj.core.api.Assertions;
import org.example.api.Requests;
import org.example.models.CreateUser;
import org.example.models.UpdateUser;
import org.junit.jupiter.api.Test;

public class UpdateUserPatchTest {
    @Test
    public void test() {
        CreateUser expectedUpdateUser = new CreateUser("morpheus", "zion resident");

        UpdateUser actualUpdateUser = Requests
                .checkStatusCodePatch("/users/2", 200, expectedUpdateUser)
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/UpdateUser.json"))
                .extract()
                .as(UpdateUser.class);

        Assertions
                .assertThat(expectedUpdateUser)
                .extracting("name", "job")
                .containsExactly(actualUpdateUser.getName(), actualUpdateUser.getJob());
    }
}
