package com.testslotegrator.automation.data.response.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerResponse {
    @JsonProperty("id")
    private int id;
    @JsonProperty("country_id")
    private String countryId;
    @JsonProperty("timezone_id")
    private String timeZoneid;
    @JsonProperty("username")
    private String username;
    @JsonProperty("email")
    private String email;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("birthdate")
    private String birthdate;
    @JsonProperty("bonuses_allowed")
    private boolean bonusesAllowed;
    @JsonProperty("is_verified")
    private boolean isVerified;
    @JsonProperty("message")
    private String message;
    @JsonProperty("code")
    private int code;
    @JsonProperty("status")
    private int status;
}
