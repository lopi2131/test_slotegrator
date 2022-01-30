package com.testslotegrator.automation.data.request.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(access = AccessLevel.PUBLIC)
public class PlayerRequest {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password_change")
    private String passwordChange;
    @JsonProperty("password_repeat")
    private String passwordRepeat;
    @JsonProperty("email")
    private String email;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("currency")
    private String currency;
}
