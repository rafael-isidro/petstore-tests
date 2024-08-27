package com.restassured.petstore.tests.pet;

import com.restassured.petstore.client.UserClient;
import com.restassured.petstore.data.factory.UserDataFactory;
import com.restassured.petstore.model.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    static UserClient userClient = new UserClient();
    static UserModel user = UserDataFactory.validUser();

    @BeforeEach
    void setUp() {
        userClient.postUser(user)
            .then()
                .statusCode(200);
    }

    @Test
    public void testBuscarUserPorUsernameComSucesso() {
        userClient.getUserByUsername(user.getUsername())
            .then()
                .statusCode(200);
    }

    @Test
    public void testTentarBuscarUserPorUsernameNaoCadastrado() {
        String message = userClient.getUserByUsernameNaoCadastrado()
            .then()
                .statusCode(404)
                .extract().path("message");

        Assertions.assertEquals("User not found", message);
    }

    @Test
    public void testCadastrarUsuarioComSucesso() {
        UserModel user = UserDataFactory.validUser();

        Integer codeMessage = userClient.postUser(user)
            .then()
                .statusCode(200)
                .extract().path("code");

        Assertions.assertEquals(200, codeMessage);
    }
}
