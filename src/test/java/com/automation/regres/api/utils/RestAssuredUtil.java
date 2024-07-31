package com.automation.regres.api.utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtil {
    public static RequestSpecification getRequestSpecification() {
        return RestAssured.given()
                .baseUri("https://reqres.in")
                .basePath("/api")
                .contentType("application/json");
    }
}
