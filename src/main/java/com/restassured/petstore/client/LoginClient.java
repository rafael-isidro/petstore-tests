package com.restassured.petstore.client;

import com.restassured.petstore.model.LoginModel;
import com.restassured.petstore.model.UserModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginClient extends BaseClient {

    private final String LOGIN = "/user/login";

    public Response loginClient(UserModel user) {
        LoginModel login = new LoginModel();
        login.setUsername(user.getUsername());
        login.setPassword(user.getPassword());

        return
            given()
                .spec(super.set())
                .contentType(ContentType.JSON)
                .body(login)
            .when()
                .get(LOGIN);

    }
}
