package ApiTests;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.assertj.core.api.Assertions;
import org.example.api.Requests;
import org.example.models.CreateUser;
import org.example.models.UpdateUser;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class UpdateUserTest {
    @Test
    public void test() {
        CreateUser expectedUpdateUser = new CreateUser("morpheus", "zion resident");

        UpdateUser actualUpdateUser = Requests
                .checkStatusCodePut("/users/2", 200, expectedUpdateUser)
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/UpdateUser.json")
                )
                .extract()
                .as(UpdateUser.class);

        Assertions
                .assertThat(actualUpdateUser)
                .extracting("name", "job")
                .containsExactly(expectedUpdateUser.getName(), expectedUpdateUser.getJob());
    }
}
