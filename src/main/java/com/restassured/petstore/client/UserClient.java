package com.restassured.petstore.client;

import com.restassured.petstore.model.UserModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserClient extends BaseClient {

    private final String USERNAME_NAO_CADASTRADO = "XYZ120###AA";
    private final String USER ="/user";
    private final String USER_BY_ID ="/user/{username}";

    public Response getUserByUsername(String username) {
        return
            given()
                .spec(super.set())
                .pathParams("username", username)
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

    public Response getUserByUsernameNaoCadastrado() {
        return
            given()
                .spec(super.set())
                .pathParams("username", USERNAME_NAO_CADASTRADO)
            .when()
                .get(USER_BY_ID);
    }
}
