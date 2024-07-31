package com.automation.regres.api.tests;

import com.automation.regres.api.utils.RestAssuredUtil;
import io.qameta.allure.Description;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SingleUserTest {



    @Test
    @Description("Verify if user with id 2 has correct data")
    public void testRetrieveUser() {
        int userId = 2;
        Response response = RestAssuredUtil.getRequestSpecification()
                .filter(new AllureRestAssured())
                .pathParam("id", userId)
                .get("/users/{id}");

        response.then()
                .statusCode(200)
                .body("data.id", equalTo(userId))
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"));
    }

}
