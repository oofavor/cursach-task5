package WebTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.example.api.Requests;
import org.example.models.Error;
import org.example.models.ListUsers;
import org.example.models.ResponseObject;
import org.example.pages.ReqresPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class RegisterUnsuccessfulTest {
    static ReqresPage page = new ReqresPage();

    @BeforeAll
    @Step("Open page")
    public static void setup() {
        page.openPage();
    }

    @Step("Click and compare with api")
    public void click() throws JsonProcessingException {
        ResponseObject<Error> webResponse = page.postRegisterUnsuccessful();

        Error error = Requests
                .checkStatusCodePost("/register", 400, Map.of("email", "sydney@fife"))
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/Error.json")
                )
                .extract()
                .as(Error.class);

        Assertions.assertEquals(error, webResponse.getResponseObject(), "objects missmatch");
    }


    @Test
    public void test() throws JsonProcessingException {
        click();
    }
}
