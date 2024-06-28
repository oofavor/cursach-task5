package WebTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import lombok.extern.java.Log;
import org.example.api.Requests;
import org.example.models.AuthSchema;
import org.example.models.ListUsers;
import org.example.models.Login;
import org.example.models.ResponseObject;
import org.example.pages.ReqresPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LoginSuccessful {

    static ReqresPage page = new ReqresPage();

    @BeforeAll
    @Step("Open page")
    public static void setup() {
        page.openPage();
    }

    @Step("Click and compare with web")
    public void click() throws JsonProcessingException {
        ResponseObject<Login> webResponse = page.postLoginSuccessful();

        AuthSchema loginData = new AuthSchema("eve.holt@reqres.in", "cityslicka");

        Login loginResponse = Requests
                .checkStatusCodePost("/login", 200, loginData)
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/Login.json"))
                .extract()
                .as(Login.class);

        Assertions.assertEquals(loginResponse, webResponse.getResponseObject(), "objects missmatch");
    }

    @Test
    public void test() throws JsonProcessingException {
        click();
    }
}
