package WebTests;

import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.example.api.Requests;
import org.example.models.ListUsers;
import org.example.models.ResponseObject;
import org.example.pages.ReqresPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ResourceNotFound {
    static ReqresPage page = new ReqresPage();

    @BeforeAll
    @Step("Open page")
    public static void setup() {
        page.openPage();
    }
    @Step("Click and compare with api")
    public void click() {
        ResponseObject<String> webResponse = page.getSingleResourceNotFound();

        Assertions.assertEquals("{}", webResponse.getResponseObject(), "Objects mismatch");
    }

    @Test
    public void test() {
        click();
    }
}
