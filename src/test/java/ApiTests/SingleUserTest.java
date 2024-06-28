package ApiTests;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.example.api.Requests;
import org.example.models.SingleUser;
import org.example.models.User;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;

public class SingleUserTest {
    @Test
    public void test() {
        SingleUser singleUserResponse = Requests
                .checkStatusCodeGet("/users/2", 200)
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath ("schemas/SingleUser.json"))
                .extract()
                .as(SingleUser.class);

        User actualUser = singleUserResponse.getData();
        User expectedUser = new User( 2,
                "janet.weaver@reqres.in",
                "Janet",
                "Weaver",
                "https://reqres.in/img/faces/2-image.jpg"
        );

        Assertions
                .assertThat(expectedUser)
                .isEqualTo(actualUser);
    }
}
