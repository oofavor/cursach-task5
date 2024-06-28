package ApiTests;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.example.api.Requests;
import org.junit.jupiter.api.Test;


public class SingleUserNotFoundTest {
    @Test
    public void test() {
        Requests
                .checkStatusCodeGet("/users/22", 404)
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/Empty.json")
                );
    }
}