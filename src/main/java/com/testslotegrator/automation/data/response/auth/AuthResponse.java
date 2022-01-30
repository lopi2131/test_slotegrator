package com.testslotegrator.automation.data.response.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthResponse {
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private int expiresIn;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("name")
    private String name;
    @JsonProperty("message")
    private String message;
    @JsonProperty("code")
    private int code;
    @JsonProperty("status")
    private int status;
}