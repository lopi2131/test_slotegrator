package com.testslotegrator.automation.providers;

import com.github.javafaker.Faker;
import com.testslotegrator.automation.data.request.auth.AuthRequest;
import com.testslotegrator.automation.data.request.player.PlayerRequest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Locale;

public class ApiTestProvider {
    public static AuthRequest buildAuthRequest(String grantType, String scope){
        return AuthRequest.builder()
                .grantType(grantType)
                .scope(scope)
                .build();
    }

    public static AuthRequest buildNewPlayerAuthRequest(String grantType, String username,
                                                        String password){
        return AuthRequest.builder()
                .grantType(grantType)
                .username(username)
                .password(password)
                .build();
    }

    public static PlayerRequest buildCreateNewPlayerRequest(){
        Faker faker = new Faker(new Locale("en"));
        String password = faker.bothify("????##??#");
        String name = faker.name().firstName();

        Base64.Encoder base64Encoder = Base64.getEncoder();
        String encodedString = base64Encoder.encodeToString(password.getBytes(StandardCharsets.UTF_8));

        return PlayerRequest.builder()
                .currency("RUR")
                .email(faker.bothify(name + "????##@gmail.com"))
                .name(name)
                .passwordChange(encodedString)
                .passwordRepeat(encodedString)
                .surname(faker.name().lastName())
                .username(faker.name().username())
                .build();
    }
}
