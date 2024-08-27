package com.restassured.petstore.tests.pet;

import com.restassured.petstore.client.UserClient;
import com.restassured.petstore.data.factory.UserDataFactory;
import com.restassured.petstore.model.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserPostTest {

    UserClient userClient = new UserClient();

    @Test
    public void testCadastrarUsuarioComSucesso() {
        UserModel user = UserDataFactory.newUser();
        Integer codeMessage = userClient.postUser(user)
            .then()
                .statusCode(200)
                .extract().path("code")
                ;
        Assertions.assertEquals(200, codeMessage);
    }
}
