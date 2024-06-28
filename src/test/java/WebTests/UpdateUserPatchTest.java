package WebTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.example.api.Requests;
import org.example.models.CreateUser;
import org.example.models.ListUsers;
import org.example.models.ResponseObject;
import org.example.models.UpdateUser;
import org.example.pages.ReqresPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UpdateUserPatchTest {
    static ReqresPage page = new ReqresPage();

    @BeforeAll
    @Step("Open page")
    public static void setup() {
        page.openPage();
    }
    @Step("Click and compare with api")
    public void click() throws JsonProcessingException {
        ResponseObject<UpdateUser> webResponse = page.patchUpdateUser();
        CreateUser expectedUpdateUser = new CreateUser("morpheus", "zion resident");

        UpdateUser actualUpdateUser = Requests
                .checkStatusCodePatch("/users/2", 200, expectedUpdateUser)
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/UpdateUser.json"))
                .extract()
                .as(UpdateUser.class);

        Assertions.assertEquals(actualUpdateUser.getJob(), webResponse.getResponseObject().getJob(), "objects missmatch");
        Assertions.assertEquals(actualUpdateUser.getName(), webResponse.getResponseObject().getName(), "objects missmatch");
    }
    @Test
    public void test() throws JsonProcessingException {
        click();
    }
}
