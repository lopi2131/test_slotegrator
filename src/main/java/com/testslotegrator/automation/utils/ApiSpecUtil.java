package com.testslotegrator.automation.utils;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

public class ApiSpecUtil {

    public static final ResponseSpecification JSON_STATUS_OK = new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_OK)
            .expectContentType(ContentType.JSON)
            .build();

    public static final ResponseSpecification JSON_STATUS_CREATED = new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_CREATED)
            .expectContentType(ContentType.JSON)
            .build();

    public static final ResponseSpecification JSON_STATUS_NOT_FOUND = new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_NOT_FOUND)
            .expectContentType(ContentType.JSON)
            .build();
}
