package com.testslotegrator.automation.steps.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testslotegrator.automation.data.request.player.PlayerRequest;
import com.testslotegrator.automation.data.response.player.PlayerResponse;
import com.testslotegrator.automation.utils.AllureReportUtil;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.SneakyThrows;

import static com.testslotegrator.automation.constants.Constants.BASE_URL_API;
import static com.testslotegrator.automation.utils.ApiSpecUtil.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerSteps {
    @Step("Регистрация игрока /v2/players")
    @SneakyThrows
    public static PlayerResponse postNewPlayerRequest(PlayerRequest playerRequest, String token) {
        ObjectMapper objectMapper = new ObjectMapper();
        Response playerResponse = given()
                .when()
                .log()
                .all()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(playerRequest)
                .post(BASE_URL_API + "/v2/players")
                .then()
                .log()
                .all()
                .spec(JSON_STATUS_CREATED)
                .extract()
                .response();

        AllureReportUtil.attachJSONToAllure("Тело запроса: ", objectMapper.writeValueAsString(playerRequest));
        AllureReportUtil.attachJSONToAllure("Тело ответа: ",
                objectMapper.writeValueAsString(objectMapper.readValue(playerResponse.body().prettyPrint(), PlayerResponse.class)));

        return objectMapper.readValue(playerResponse.body().prettyPrint(), PlayerResponse.class);
    }

    @Step("Запрашиваем данные игрока /v2/players/{playerId}")
    @SneakyThrows
    public static PlayerResponse getPlayerProfileRequest(String token, int playerId) {
        ObjectMapper objectMapper = new ObjectMapper();
        Response playerResponse = given()
                .when()
                .log()
                .all()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .get(BASE_URL_API + "/v2/players/" + playerId)
                .then()
                .log()
                .all()
                .spec(JSON_STATUS_OK)
                .extract()
                .response();

        AllureReportUtil.attachJSONToAllure("Тело ответа: ",
                objectMapper.writeValueAsString(objectMapper.readValue(playerResponse.body().prettyPrint(), PlayerResponse.class)));

        return objectMapper.readValue(playerResponse.body().prettyPrint(), PlayerResponse.class);
    }

    @Step("Запрашиваем данные другого игрока /v2/players/{playerId}")
    @SneakyThrows
    public static PlayerResponse getOtherPlayerProfileRequest(String token, int playerId) {
        ObjectMapper objectMapper = new ObjectMapper();
        Response playerResponse = given()
                .when()
                .log()
                .all()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .get(BASE_URL_API + "/v2/players/" + playerId)
                .then()
                .log()
                .all()
                .spec(JSON_STATUS_NOT_FOUND)
                .extract()
                .response();

        AllureReportUtil.attachJSONToAllure("Тело ответа: ",
                objectMapper.writeValueAsString(objectMapper.readValue(playerResponse.body().prettyPrint(), PlayerResponse.class)));

        return objectMapper.readValue(playerResponse.body().prettyPrint(), PlayerResponse.class);
    }

    @Step("Проверка ответа")
    public static void checkPlayer(PlayerResponse response, PlayerRequest playerRequest) {
        assertAll("Ответ от сервиса отличается от ожидаемого",
                () -> assertNull(response.getBirthdate()),
                () -> assertNull(response.getCountryId()),
                () -> assertNull(response.getTimeZoneid()),
                () -> assertNull(response.getGender()),
                () -> assertNull(response.getPhoneNumber()),
                () -> assertTrue(response.isBonusesAllowed()),
                () -> assertFalse(response.isVerified()),
                () -> assertNotNull(response.getId()),
                () -> assertEquals(playerRequest.getUsername(), response.getUsername(),
                        () -> "Поле в ответе отличается от " + playerRequest.getUsername()),
                () -> assertEquals(playerRequest.getName(), response.getName(),
                        () -> "Поле в ответе отличается от " + playerRequest.getName()),
                () -> assertEquals(playerRequest.getSurname(), response.getSurname(),
                        () -> "Поле в ответе отличается от " + playerRequest.getSurname()),
                () -> assertEquals(playerRequest.getEmail(), response.getEmail(),
                        () -> "Поле в ответе отличается от " + playerRequest.getEmail()));
    }

    @Step("Проверка ответа с ошибкой")
    public static void checkError(PlayerResponse response, int playerId) {
        assertAll("Ответ от сервиса отличается от ожидаемого",
                () -> assertEquals("Not Found", response.getName(),
                        () -> "Поле в ответе отличается от " + "Not Found"),
                () -> assertEquals("Object not found: " + playerId, response.getMessage(),
                        () -> "Поле в ответе отличается от " + "Object not found: " + playerId),
                () -> assertEquals(0, response.getCode(),
                        () -> "Поле в ответе отличается от " + 0));
    }
}
