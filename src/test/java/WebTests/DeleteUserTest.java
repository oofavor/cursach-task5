package WebTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import org.example.api.Requests;
import org.example.models.ResponseObject;
import org.example.pages.ReqresPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.emptyOrNullString;

public class DeleteUserTest {
    static ReqresPage page = new ReqresPage();

    @BeforeAll
    @Step("Open page")
    public static void setup() {
        page.openPage();
    }

    @Step("Delete user and compare")
    public void click() throws JsonProcessingException {
        ResponseObject<String> responseObject = page.deleteUser();

        Assertions.assertEquals("", responseObject.getResponseObject(), "objects missmatch");
    }

    @Test
    public void test() throws JsonProcessingException {
        click();
    }
}
