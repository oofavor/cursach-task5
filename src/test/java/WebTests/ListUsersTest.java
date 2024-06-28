package WebTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.example.api.Requests;
import org.example.models.ListResources;
import org.example.models.ListUsers;
import org.example.models.ResponseObject;
import org.example.models.User;
import org.example.pages.ReqresPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;


public class ListUsersTest {
    static ReqresPage page = new ReqresPage();

    @BeforeAll
    @Step("Open page")
    public static void setup() {
        page.openPage();
    }

    @Step("Click button and compare with web")
    public void click() throws JsonProcessingException {
        ResponseObject<ListUsers> webResponse = page.getListUsers();

        ListUsers listUsers = Requests
                .checkStatusCodeGet("/users?page=2", 200)
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/ListUsers.json")
                )
                .extract()
                .as(ListUsers.class);

        Assertions.assertEquals(listUsers, webResponse.getResponseObject(), "objects missmatch");
    }

    @Test
    public void test() throws JsonProcessingException {
        click();
    }
}
