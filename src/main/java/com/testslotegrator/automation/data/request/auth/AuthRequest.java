package com.testslotegrator.automation.data.request.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Setter;

@Setter
@Builder(access = AccessLevel.PUBLIC)
public class AuthRequest {
    @JsonProperty("grant_type")
    private String grantType;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
}
