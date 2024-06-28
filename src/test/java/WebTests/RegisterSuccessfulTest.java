package WebTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.example.api.Requests;
import org.example.models.AuthSchema;
import org.example.models.ListUsers;
import org.example.models.Register;
import org.example.models.ResponseObject;
import org.example.pages.ReqresPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class RegisterSuccessfulTest {
    static ReqresPage page = new ReqresPage();

    @BeforeAll
    @Step("Open page")
    public static void setup() {
        page.openPage();
    }

    @Step("Click and compare with web")
    public void click() throws JsonProcessingException {
        ResponseObject<Register> webResponse = page.postRegisterSuccessful();
        AuthSchema registerObject = new AuthSchema("eve.holt@reqres.in", "pistol");

        Register registerResponse = Requests
                .checkStatusCodePost("/register", 200, registerObject)
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/Register.json"))
                .extract()
                .as(Register.class);

        Assertions.assertEquals(registerResponse, webResponse.getResponseObject(), "objects missmatch");
    }

    @Test
    public void test() throws JsonProcessingException {
        click();
    }
}
