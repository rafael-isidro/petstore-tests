package com.restassured.petstore.tests.pet;

import com.restassured.petstore.client.PetClient;
import org.junit.jupiter.api.Test;

public class PetGetTest {

    static PetClient petClient = new PetClient();

    @Test
    public void testBuscarPetPorIdComSucesso() {
        petClient.getPetById()
            .then()
                .assertThat().statusCode(200);
    }
}
