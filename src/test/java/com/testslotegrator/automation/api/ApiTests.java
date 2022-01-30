package com.testslotegrator.automation.api;

import com.github.javafaker.Faker;
import com.testslotegrator.automation.data.request.player.PlayerRequest;
import com.testslotegrator.automation.data.response.player.PlayerResponse;
import com.testslotegrator.automation.tags.Api;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.testslotegrator.automation.providers.ApiTestProvider.*;
import static com.testslotegrator.automation.steps.api.AuthSteps.checkToken;
import static com.testslotegrator.automation.steps.api.AuthSteps.postAuthRequest;
import static com.testslotegrator.automation.steps.api.PlayerSteps.*;
import static java.lang.Math.random;


@DisplayName("Api tests")
public class ApiTests {

    @Api
    @Test
    @SneakyThrows
    @DisplayName("Получить токен гостя (Client Credentials Grant, scope — guest:default)")
    public void authGuestTest() {
        checkToken(postAuthRequest(
                buildAuthRequest("client_credentials", "guest:default"),
                "front_2d6b0a8391742f5d789d7d915755e09e").getAccessToken());
    }

    @Api
    @Test
    @SneakyThrows
    @DisplayName("Зарегистрировать игрока")
    public void playerRegistrationTest() {
        String token = postAuthRequest(
                buildAuthRequest("client_credentials", "guest:default"),
                "front_2d6b0a8391742f5d789d7d915755e09e").getAccessToken();

        PlayerRequest playerRequest = buildCreateNewPlayerRequest();

        checkPlayer(postNewPlayerRequest(playerRequest, token), playerRequest);
    }

    @Api
    @Test
    @SneakyThrows
    @DisplayName("Авторизоваться под созданным игроком(Resource Owner Password Credentials Grant)")
    public void newPlayerAuthTest() {
        String token = postAuthRequest(
                buildAuthRequest("client_credentials", "guest:default"),
                "front_2d6b0a8391742f5d789d7d915755e09e").getAccessToken();
        PlayerRequest newPlayerRequest = buildCreateNewPlayerRequest();
        PlayerResponse newPlayerResponse = postNewPlayerRequest(newPlayerRequest, token);
        checkToken(postAuthRequest(
                buildNewPlayerAuthRequest("password", newPlayerRequest.getUsername(), newPlayerRequest.getPasswordChange()),
                "front_2d6b0a8391742f5d789d7d915755e09e").getAccessToken());

    }

    @Api
    @Test
    @SneakyThrows
    @DisplayName("Запросить данные профиля игрока")
    public void playerProfileTest() {
        String token = postAuthRequest(
                buildAuthRequest("client_credentials", "guest:default"),
                "front_2d6b0a8391742f5d789d7d915755e09e").getAccessToken();
        PlayerRequest newPlayerRequest = buildCreateNewPlayerRequest();
        PlayerResponse newPlayerResponse = postNewPlayerRequest(newPlayerRequest, token);
        checkPlayer(getPlayerProfileRequest(token, newPlayerResponse.getId()), newPlayerRequest);
    }

    @Api
    @Test
    @SneakyThrows
    @DisplayName("Запросить данные другого игрока")
    public void otherPlayerProfileTest() {
        Faker faker = new Faker();
        String token = postAuthRequest(
                buildAuthRequest("client_credentials", "guest:default"),
                "front_2d6b0a8391742f5d789d7d915755e09e").getAccessToken();
        PlayerRequest newPlayerRequest = buildCreateNewPlayerRequest();
        PlayerResponse newPlayerResponse = postNewPlayerRequest(newPlayerRequest, token);
        int otherPlayerId = faker.number().numberBetween(1, newPlayerResponse.getId());
        checkError(getOtherPlayerProfileRequest(token, otherPlayerId), otherPlayerId);
    }
}
