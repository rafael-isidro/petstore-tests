package com.restassured.petstore.client;

import com.restassured.petstore.model.UserModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserClient extends BaseClient {

    private final String USERNAME_NAO_CADASTRADO = "XYZ120###AA";
    private final String USER ="/user";
    private final String USER_BY_USERNAME ="/user/{username}";

    public Response getUserByUsername(String username) {
        return
            given()
                .spec(super.set())
                .pathParams("username", username)
                .when()
                .get(USER_BY_USERNAME);
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
                .get(USER_BY_USERNAME);
    }

    public Response putUser(UserModel user, UserModel newUser) {
        return
            given()
                .spec(super.set())
                .contentType(ContentType.JSON)
                .pathParams("username", user.getUsername())
                .body(newUser)
            .when()
                .put(USER_BY_USERNAME);
    }

    public Response deleteUser(UserModel user) {
        return
            given()
                .spec(super.set())
                .contentType(ContentType.JSON)
                .pathParams("username", user.getUsername())
            .when()
                .delete(USER_BY_USERNAME);
    }

    public Response deleteUserByUsernameNaoCadastrado() {
        return
            given()
                .spec(super.set())
                .contentType(ContentType.JSON)
                .pathParams("username", USERNAME_NAO_CADASTRADO)
            .when()
                .delete(USER_BY_USERNAME);
    }
}
