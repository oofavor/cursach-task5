package ApiTests;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.assertj.core.api.Assertions;
import org.example.api.Requests;
import org.example.models.ListUsers;
import org.example.models.User;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DelayedResponseTest {
    @Test
    public void test() {
        ListUsers listUsersResponse = Requests
                .checkStatusCodeGetWithoutTime("/users?delay=3", 200)
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/ListUsers.json")
                )
                .extract()
                .as(ListUsers.class);

        Assertions.assertThat(listUsersResponse)
                .isNotNull()
                .extracting("page", "perPage", "total", "totalPages")
                .containsExactly(1, 6, 12, 2);

        List<User> users = listUsersResponse.getData();

        Assertions.assertThat(users).allMatch(user -> user.getId() != null);
    }
}
