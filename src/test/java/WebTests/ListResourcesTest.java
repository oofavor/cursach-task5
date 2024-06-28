package WebTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.checkerframework.checker.units.qual.C;
import org.example.api.Requests;
import org.example.models.ListResources;
import org.example.models.Resource;
import org.example.models.ResponseObject;
import org.example.pages.ReqresPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ListResourcesTest {
    static ReqresPage page = new ReqresPage();

    @BeforeAll
    @Step("Open page")
    public static void setup() {
        page.openPage();
    }

    @Step("Click button and compare results")
    public void click() throws JsonProcessingException {
        ListResources listResources = Requests
                .checkStatusCodeGet("/unknown", 200)
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/ListResource.json")
                )
                .extract()
                .as(ListResources.class);

        ResponseObject<ListResources> webResponse = page.getListResources();

        Assertions.assertEquals(listResources, webResponse.getResponseObject(), "object missmatch");
    }
    @Test
    public void test() throws JsonProcessingException {
        click();
    }
}
