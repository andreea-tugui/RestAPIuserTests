package com.automation.regres.api.tests;

import com.automation.regres.api.utils.RestAssuredUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class LoginSuccessfulTest {

    @Test
    @Description("Verify login successfully")
    public void loginSuccessfulTest() {
        String email = "eve.holt@reqres.in";
        String password = "cityslicka";

        String requestBody = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        Allure.addAttachment("Request Body", "application/json", requestBody, "json");

        Response response = RestAssuredUtil.getRequestSpecification()
                .filter(new AllureRestAssured())
                .body(requestBody)
                .post("/login");

        response.then()
                .statusCode(200)
                .body("token", notNullValue());

        Allure.addAttachment("Response", "application/json", response.asString(), "json");
    }
}
