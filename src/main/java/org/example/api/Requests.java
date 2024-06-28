package org.example.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.models.ResponseObject;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class Requests {
    public static final String BASE_URL = "https://reqres.in/api";
    private static RequestSpecification req = given()
                .baseUri(BASE_URL)
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);

    public static ValidatableResponse checkStatusCodeGet(String url, int code) {
        return given(req)
                .get(url)
                .then()
                .log().all()
                .statusCode(code);
    }

    public static ValidatableResponse checkStatusCodeGetWithoutTime(String url, int code) {
        return given(req)
                .get(url)
                .then()
                .log().all()
                .statusCode(code);
    }

    public static ValidatableResponse checkStatusCodePost(String url, int code, Object body) {
        return given(req)
                .body(body)
                .post(url)
                .then()
                .log().all()
                .statusCode(code);
    }

    public static ValidatableResponse checkStatusCodePut(String url, int code, Object body) {
        return given(req)
                .body(body)
                .put(url)
                .then()
                .log().all()
                .statusCode(code);
    }

    public static ValidatableResponse checkStatusCodePatch(String url, int code, Object body) {
        return given(req)
                .body(body)
                .patch(url)
                .then()
                .log().all()
                .statusCode(code);
    }

    public static ValidatableResponse checkStatusCodeDelete(String url, int code) {
        return given(req)
                .delete(url)
                .then()
                .log().all()
                .statusCode(code);
    }

    public static ValidatableResponse specialCheckStatusCodeGet(String url, int code) {
        return given(req)
                .baseUri(BASE_URL)
                .log()
                .all()
                .contentType(ContentType.TEXT)
                .accept(ContentType.TEXT)
                .get(url)
                .then()
                .log().all()
                .statusCode(code);
    }
}
