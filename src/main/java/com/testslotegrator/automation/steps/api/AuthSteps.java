package com.testslotegrator.automation.steps.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testslotegrator.automation.data.request.auth.AuthRequest;
import com.testslotegrator.automation.data.response.auth.AuthResponse;
import com.testslotegrator.automation.utils.AllureReportUtil;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.SneakyThrows;

import static com.testslotegrator.automation.constants.Constants.BASE_URL_API;
import static com.testslotegrator.automation.utils.ApiSpecUtil.JSON_STATUS_OK;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthSteps {

    @Step("Авторизация /v2/oauth2/token")
    @SneakyThrows
    public static AuthResponse postAuthRequest(AuthRequest authRequest, String username) {
        ObjectMapper objectMapper = new ObjectMapper();
        Response authResponse = given()
                .when()
                .log()
                .all()
                .auth()
                .basic(username, "")
                .header("Authorization", "Basic ZnJvbnRfMmQ2YjBhODM5MTc0MmY1ZDc4OWQ3ZDkxNTc1NWUwOWU6")
                .header("Content-Type", "application/json")
                .body(authRequest)
                .post(BASE_URL_API + "/v2/oauth2/token")
                .then()
                .log()
                .all()
                .spec(JSON_STATUS_OK)
                .extract()
                .response();

        AllureReportUtil.attachJSONToAllure("Тело запроса: ", objectMapper.writeValueAsString(authRequest));
        AllureReportUtil.attachJSONToAllure("Тело ответа: ",
                objectMapper.writeValueAsString(objectMapper.readValue(authResponse.body().prettyPrint(), AuthResponse.class)));

        return objectMapper.readValue(authResponse.body().prettyPrint(), AuthResponse.class);
    }

    @Step("Проверка {token}")
    public static void checkToken(String token) {
        assertAll("Ответ от сервиса отличается от ожидаемого",
                () -> assertNotNull(token, "Токен пустой"));
    }
}
