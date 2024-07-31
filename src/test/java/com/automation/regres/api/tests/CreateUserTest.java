package com.automation.regres.api.tests;

import com.automation.regres.api.utils.RestAssuredUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class CreateUserTest {

    @Test
    @Description("Verify user creation")
    public void createUserTest() {
        String name = "Test name";
        String job = "Automation tester";

        String requestBody = String.format("{\"name\": \"%s\", \"job\": \"%s\"}", name, job);
        Allure.addAttachment("Request Body", "application/json", requestBody, "json");

        Response response = RestAssuredUtil.getRequestSpecification()
                .filter(new AllureRestAssured())
                .body(requestBody)
                .post("/users");

        response.then()
                .statusCode(201)
                .body("name", equalTo(name))
                .body("job", equalTo(job))
                .body("id", notNullValue())
                .body("createdAt", notNullValue());


        Allure.addAttachment("Response", "application/json", response.asString(), "json");
    }
}
