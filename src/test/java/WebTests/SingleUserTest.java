package WebTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.example.api.Requests;
import org.example.models.ListUsers;
import org.example.models.ResponseObject;
import org.example.models.SingleUser;
import org.example.models.User;
import org.example.pages.ReqresPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingleUserTest {
    private static final Logger log = LoggerFactory.getLogger(SingleUserTest.class);
    static ReqresPage page = new ReqresPage();

    @BeforeAll
    @Step("Open page")
    public static void setup() {
        page.openPage();
    }
    @Step("Click and compare with api")
    public void click() throws JsonProcessingException {
            ResponseObject<SingleUser> webResponse = page.getSingleUser();

            SingleUser singleUserResponse = Requests
                    .checkStatusCodeGet("/users/2", 200)
                    .assertThat()
                    .body(
                            JsonSchemaValidator
                                    .matchesJsonSchemaInClasspath ("schemas/SingleUser.json"))
                    .extract()
                    .as(SingleUser.class);

            Assertions.assertEquals(singleUserResponse, webResponse.getResponseObject(), "objects missmatch");
    }
    @Test
    public void test() throws JsonProcessingException {
        click();
    }
}
