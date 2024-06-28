package WebTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.example.api.Requests;
import org.example.models.CreateUser;
import org.example.models.ResponseObject;
import org.example.pages.ReqresPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CreateUserTest {
    static ReqresPage page = new ReqresPage();

    @BeforeAll
    @Step("Open page")
    public static void setup() {
        page.openPage();
    }

    @Step("Click on the button and compare")
    public void click() throws JsonProcessingException {
        ResponseObject<CreateUser> responseObject = page.postCreateUser();

        CreateUser expectedCreatedUser = new CreateUser("morpheus", "leader");

        CreateUser actualCreatedUser = Requests
                .checkStatusCodePost("/users", responseObject.getResponseCode(), expectedCreatedUser)
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/CreateUser.json")
                )
                .extract()
                .as(CreateUser.class);

        Assertions.assertEquals(actualCreatedUser, responseObject.getResponseObject());
    }

    @Test
    public void test() throws JsonProcessingException {
        click();
    }
}