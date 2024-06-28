package ApiTests;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.example.api.Requests;
import org.example.models.ListResources;
import org.example.models.Resource;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ListResourcesTest {
    @Test
    public void test() {
        ListResources listResources = Requests
                .checkStatusCodeGet("/unknown", 200)
                .assertThat()
                .body(
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/ListResource.json")
                )
                .extract()
                .as(ListResources.class);

        List<Resource> resources = listResources.getData();

        Assertions.assertThat(resources).allMatch(resource -> resource.getId() != null);
    }
}
