package ApiTests;

import org.example.api.Requests;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.emptyOrNullString;

public class DeleteUserTest {
    @Test
    public void test(){
            Requests
                    .checkStatusCodeDelete("/users/2", 204)
                    .assertThat()
                    .body(emptyOrNullString());
    }
}
