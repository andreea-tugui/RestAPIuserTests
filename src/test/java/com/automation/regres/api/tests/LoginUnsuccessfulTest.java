package com.automation.regres.api.tests;

import com.automation.regres.api.utils.RestAssuredUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class LoginUnsuccessfulTest {

    @Test
    @Description("Verify login unsuccessfully without password")
    public void loginUnsuccessfulWithoutPasswordTest() {
        String email = "eve.holt@reqres.in";


        String requestBody = String.format("{\"email\": \"%s\"}", email);

        Allure.addAttachment("Request Body", "application/json", requestBody, "json");

        Response response = RestAssuredUtil.getRequestSpecification()
                .filter(new AllureRestAssured())
                .body(requestBody)
                .post("/login");

        response.then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));

        Allure.addAttachment("Response", "application/json", response.asString(), "json");
    }

    @Test
    @Description("Verify login unsuccessfully with empty password")
    public void loginUnsuccessfulWithEmptyPasswordTest() {
        String email = "eve.holt@reqres.in";

        String password = "";

        String requestBody = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        Allure.addAttachment("Request Body", "application/json", requestBody, "json");

        Response response = RestAssuredUtil.getRequestSpecification()
                .filter(new AllureRestAssured())
                .body(requestBody)
                .post("/login");

        response.then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));

        Allure.addAttachment("Response", "application/json", response.asString(), "json");
    }
}
