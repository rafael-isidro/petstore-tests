package com.restassured.petstore.tests.pet;

import com.restassured.petstore.client.PetClient;
import com.restassured.petstore.client.UserClient;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class UserGetTest {

    static UserClient userClient = new UserClient();

    @Test
    public void testBuscarUserPorIdComSucesso() {
        userClient.getUserByUsername()
            .then()
                .assertThat().statusCode(200);
    }
}
