package org.example.pages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.*;
import org.example.models.Error;
import org.example.utils.WebDriverInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReqresPage {
    WebDriver driver = WebDriverInstance.getInstance();

    By getListUsers = By.cssSelector("*[data-id='users'] > a");
    By getSingleUser = By.cssSelector("*[data-id='users-single'] > a");
    By getSingleUserNotFound = By.cssSelector("*[data-id='users-single-not-found'] > a");
    By getListResources = By.cssSelector("*[data-id='unknown'] > a");
    By getSingleResource = By.cssSelector("*[data-id='unknown-single'] > a");
    By getSingleResourceNotFound = By.cssSelector("*[data-id='unknown-single-not-found'] > a");
    By postCreateUser = By.cssSelector("*[data-id='post'] > a");
    By putUpdateUser = By.cssSelector("*[data-id='put'] > a");
    By patchUpdateUser = By.cssSelector("*[data-id='patch'] > a");
    By deleteUser = By.cssSelector("*[data-id='delete'] > a");
    By postRegisterSuccessful = By.cssSelector("*[data-id='register-successful'] > a");
    By postRegisterUnsuccessful = By.cssSelector("*[data-id='register-unsuccessful'] > a");
    By postLoginSuccessful = By.cssSelector("*[data-id='login-successful'] > a");
    By postLoginUnsuccessful = By.cssSelector("*[data-id='login-unsuccessful'] > a");
    By getDelayedUsers = By.cssSelector("*[data-id='delay'] > a");

    By responseField = By.cssSelector("[data-key='output-response']");
    By requestField = By.cssSelector("[data-key='output-request']");

    By responseCode = By.cssSelector("[data-key='response-code']");

    public void openPage() {
        driver.get("https://reqres.in/");
    }

    public void request(By locate) {
        WebDriverInstance.waitTillAppear(locate);
        WebDriverInstance.scrollTo(locate);

        driver.findElement(locate).click();

        WebDriverInstance.waitTillVisible(responseField);
        WebDriverInstance.scrollTo(responseField);
    }

    protected <T> T parseResponseJson(Class<T> clazz) throws JsonProcessingException {
        String json = driver.findElement(responseField).getText();
        return new ObjectMapper().readValue(json, clazz);
    }

    protected int getResponseCode() {
        return Integer.parseInt(driver.findElement(responseCode).getText());
    }

    public ResponseObject<ListUsers> getListUsers() throws JsonProcessingException {
        request(getListUsers);
        ListUsers response = parseResponseJson(ListUsers.class);
        int responseCode = getResponseCode();
        return new ResponseObject<>(response, responseCode);
    }

    public ResponseObject<SingleUser> getSingleUser() throws JsonProcessingException {
        request(getSingleUser);
        SingleUser response = parseResponseJson(SingleUser.class);
        int responseCode = getResponseCode();
        return new ResponseObject<>(response, responseCode);
    }

    public ResponseObject<String> getSingleUserNotFound() {
        request(getSingleUserNotFound);
        return new ResponseObject<>(driver.findElement(responseField).getText(), getResponseCode());
    }

    public ResponseObject<ListResources> getListResources() throws JsonProcessingException {
        request(getListResources);
        ListResources response = parseResponseJson(ListResources.class);
        int responseCode = getResponseCode();
        return new ResponseObject<>(response, responseCode);
    }

    public ResponseObject<SingleResource> getSingleResource() throws JsonProcessingException {
        request(getSingleResource);
        SingleResource response = parseResponseJson(SingleResource.class);
        int responseCode = getResponseCode();
        return new ResponseObject<>(response, responseCode);
    }

    public ResponseObject<String> getSingleResourceNotFound() {
        request(getSingleResourceNotFound);
        return new ResponseObject<>(driver.findElement(responseField).getText(), getResponseCode());
    }

    public ResponseObject<CreateUser> postCreateUser() throws JsonProcessingException {
        request(postCreateUser);
        CreateUser response = parseResponseJson(CreateUser.class);
        int responseCode = getResponseCode();
        return new ResponseObject<>(response, responseCode);
    }

    public ResponseObject<UpdateUser> putUpdateUser() throws JsonProcessingException {
        request(putUpdateUser);
        UpdateUser response = parseResponseJson(UpdateUser.class);
        int responseCode = getResponseCode();
        return new ResponseObject<>(response, responseCode);
    }

    public ResponseObject<UpdateUser> patchUpdateUser() throws JsonProcessingException {
        request(patchUpdateUser);
        UpdateUser response = parseResponseJson(UpdateUser.class);
        int responseCode = getResponseCode();
        return new ResponseObject<>(response, responseCode);
    }

    public ResponseObject<String> deleteUser() throws JsonProcessingException {
        request(deleteUser);
        return new ResponseObject<>("", getResponseCode());
    }

    public ResponseObject<Register> postRegisterSuccessful() throws JsonProcessingException {
        request(postRegisterSuccessful);
        Register response = parseResponseJson(Register.class);
        int responseCode = getResponseCode();
        return new ResponseObject<>(response, responseCode);
    }

    public ResponseObject<Error> postRegisterUnsuccessful() throws JsonProcessingException {
        request(postRegisterUnsuccessful);
        Error response = parseResponseJson(Error.class);
        int responseCode = getResponseCode();
        return new ResponseObject<>(response, responseCode);
    }

    public ResponseObject<Login> postLoginSuccessful() throws JsonProcessingException {
        request(postLoginSuccessful);
        Login response = parseResponseJson(Login.class);
        int responseCode = getResponseCode();
        return new ResponseObject<>(response, responseCode);
    }

    public ResponseObject<Error> postLoginUnsuccessful() throws JsonProcessingException {
        request(postLoginUnsuccessful);
        Error response = parseResponseJson(Error.class);
        int responseCode = getResponseCode();
        return new ResponseObject<>(response, responseCode);
    }

    public ResponseObject<ListUsers> getDelayedUsers() throws JsonProcessingException {
        request(getDelayedUsers);
        ListUsers response = parseResponseJson(ListUsers.class);
        int responseCode = getResponseCode();
        return new ResponseObject<>(response, responseCode);
    }
}

