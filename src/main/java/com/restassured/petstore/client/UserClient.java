package com.restassured.petstore.client;

import com.restassured.petstore.model.UserModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserClient extends BaseClient {

    private static final String VALID_USERNAME = "john1234";
    private final String USER ="/user";
    private final String USER_BY_ID ="/user/{username}";

    public Response getUserByUsername() {
        return
            given()
                .spec(super.set())
                .pathParams("username", VALID_USERNAME)
                .when()
                .get(USER_BY_ID);
    }

    public Response postUser(UserModel user) {
        return
            given()
                .spec(super.set())
                .contentType(ContentType.JSON)
                .body(user)
            .when()
                .post(USER);
    }
}
