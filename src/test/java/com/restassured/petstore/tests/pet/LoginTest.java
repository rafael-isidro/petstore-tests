package com.restassured.petstore.tests.pet;

import com.restassured.petstore.client.LoginClient;
import com.restassured.petstore.client.UserClient;
import com.restassured.petstore.data.factory.UserDataFactory;
import com.restassured.petstore.model.UserModel;
import com.restassured.petstore.model.response.UserResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LoginTest {
    static LoginClient loginClient = new LoginClient();
    static UserModel user;

    @BeforeAll
    public static void setup() {
        user = UserDataFactory.validUser();
        UserClient userClient = new UserClient();
        userClient.postUser(user)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testLoginComSucesso() {
        UserResponse response = loginClient.loginClient(user)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().as(UserResponse.class);

        Assertions.assertTrue(response.getMessage().contains("logged in user session"));
        Assertions.assertEquals(response.getCode(), HttpStatus.SC_OK);
    }

    // BUG: O sistema retorna mensagem e código de login bem sucedido mesmo informando dados vazios
    @Test
    public void testTentarLogarComCredenciaisEmBranco() {
        UserModel invalidUser = UserDataFactory.userEmptyFields();

        UserResponse response = loginClient.loginClient(invalidUser)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().response().as(UserResponse.class);

        Assertions.assertTrue(response.getMessage().contains("valid username and password is required"));
        Assertions.assertEquals(response.getCode(), HttpStatus.SC_BAD_REQUEST);
    }

    // BUG: O sistema retorna mensagem e código de login bem sucedido mesmo campo username estando nulo
    @Test
    public void testTentarLogarComUsernameNulo() {
        UserModel invalidUser = UserDataFactory.userNullUsername();

        UserResponse response = loginClient.loginClient(invalidUser)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().response().as(UserResponse.class);

        Assertions.assertTrue(response.getMessage().contains("valid username and password is required"));
        Assertions.assertEquals(response.getCode(), HttpStatus.SC_BAD_REQUEST);
    }

    // BUG: O sistema retorna mensagem e código de login bem sucedido mesmo campo username estando nulo
    @Test
    public void testTentarLogarComSenhaNula() {
        UserModel invalidUser = UserDataFactory.userNullPassword();

        UserResponse response = loginClient.loginClient(invalidUser)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().response().as(UserResponse.class);

        Assertions.assertTrue(response.getMessage().contains("valid username and password is required"));
        Assertions.assertEquals(response.getCode(), HttpStatus.SC_BAD_REQUEST);
    }
}
