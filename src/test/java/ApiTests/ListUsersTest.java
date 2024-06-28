package ApiTests;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.assertj.core.api.Assertions;
import org.example.api.Requests;
import org.example.models.ListUsers;
import org.example.models.User;
import org.junit.jupiter.api.Test;

import java.util.List;


public class ListUsersTest {
    @Test
    public void test() {
        ListUsers listUsers = Requests
                .checkStatusCodeGet("/users?page=2", 200)
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/ListUsers.json")
                )
                .extract()
                .as(ListUsers.class);

        Assertions.assertThat(listUsers)
                .isNotNull()
                .extracting("page", "perPage", "total", "totalPages")
                .containsExactly(2, 6, 12, 2);

        List<User> users = listUsers.getData();

        Assertions.assertThat(users)
                .allMatch(user -> user.getId() != null);

        Assertions.assertThat(users)
                .anyMatch(user -> user.getFirstName().equals("Tobias") && user.getLastName().equals("Funke"));
    }
}
