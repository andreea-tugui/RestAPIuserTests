package com.automation.regres.api.tests;

import com.automation.regres.api.model.UserListResponse;
import com.automation.regres.api.utils.RestAssuredUtil;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ListUserTest {

    @Test
    @Description("Verify the List User API returns page 1 with the correct number of users (6)")
    public void testListUserPage1() {
        Response response = RestAssuredUtil.getRequestSpecification()
                .get("/users?page=1");

        response.then().statusCode(200);

        UserListResponse userListResponse = response.as(UserListResponse.class);

        assertThat(userListResponse.getPage(), is(1));
        assertThat(userListResponse.getData(), hasSize(6));
    }

    @Test
    @Description("Verify the List User API returns page 2 with the correct number of users (6)")
    public void testListUserPage2() {
        Response response = RestAssuredUtil.getRequestSpecification()
                .get("/users?page=2");

        response.then().statusCode(200);

        UserListResponse userListResponse = response.as(UserListResponse.class);

        assertThat(userListResponse.getPage(), is(2));
        assertThat(userListResponse.getData(), hasSize(6));
    }


    @Test
    @Description("Verify the List User API returns users with valid data")
    public void testListUserValidData() {
        Response response = RestAssuredUtil.getRequestSpecification()
                .get("/users?page=1");

        response.then().statusCode(200);

        UserListResponse userListResponse = response.as(UserListResponse.class);

        userListResponse.getData().forEach(user -> {
            assertThat(user.getId(), is(greaterThan(0)));
            assertThat(user.getEmail(), is(not(emptyOrNullString())));
            assertThat(user.getFirstName(), is(not(emptyOrNullString())));
            assertThat(user.getLastName(), is(not(emptyOrNullString())));
            assertThat(user.getAvatar(), is(not(emptyOrNullString())));
        });
    }


    @Test
    @Description("Verify status code 200 when requesting second page")
    void testListStatusCode(){
        Response response= RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : " +response.getStatusCode());
        System.out.println("Body : " +response.getBody().asString());
        System.out.println("Time taken : "+ response.getTime());
        System.out.println(("Header : "+ response.getHeader("content-type")));

        int statusCode=response.getStatusCode();
        Assert.assertEquals(statusCode,200);
    }
}
