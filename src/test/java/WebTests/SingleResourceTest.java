package WebTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.example.api.Requests;
import org.example.models.ListUsers;
import org.example.models.Resource;
import org.example.models.ResponseObject;
import org.example.models.SingleResource;
import org.example.pages.ReqresPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SingleResourceTest {
    static ReqresPage page = new ReqresPage();

    @BeforeAll
    @Step("Open page")
    public static void setup() {
        page.openPage();
    }

    @Step("Click and compare with api")
    public void click() throws JsonProcessingException {
        ResponseObject<SingleResource> webResponse = page.getSingleResource();
        SingleResource singleResourceResponse = Requests
                .checkStatusCodeGet("/unknown/2", 200)
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/SingleResource.json")
                )
                .extract()
                .as(SingleResource.class);

        Assertions.assertEquals(singleResourceResponse, webResponse.getResponseObject(), "objects missmatch");
    }

    @Test
    public void test() throws JsonProcessingException {
        click();
    }
}
