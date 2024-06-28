package ApiTests;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.example.api.Requests;
import org.example.models.ListResources;
import org.example.models.Resource;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.example.models.SingleResource;
import org.junit.jupiter.api.Test;

public class SingleResourceTest {
    @Test
    public void test() {
        SingleResource singleResourceResponse = Requests
                .checkStatusCodeGet("/unknown/2", 200)
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/SingleResource.json")
                )
                .extract()
                .as(SingleResource.class);

        Resource expectedResource =
                new Resource(2, "fuchsia rose", 2001, "#C74375", "17-2031");

        Resource actualResource = singleResourceResponse.getData();

        Assertions.assertThat(expectedResource).isEqualTo(actualResource);
    }
}
