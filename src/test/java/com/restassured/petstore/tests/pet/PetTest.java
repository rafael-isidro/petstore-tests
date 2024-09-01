package com.restassured.petstore.tests.pet;

import com.restassured.petstore.client.PetClient;
import com.restassured.petstore.data.factory.PetDataFactory;
import com.restassured.petstore.model.PetModel;
import com.restassured.petstore.model.response.GenericResponse;
import com.restassured.petstore.model.response.PetResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PetTest {

    static PetClient petClient = new PetClient();

    @Test
    public void testBuscarPetPorIdComSucesso() {
        petClient.getPetById()
            .then()
                .assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testTentarBuscarPetPorIdComIdNaoCadastrado() {
        GenericResponse response = petClient.getPetByNonExistingId()
            .then()
                .assertThat().statusCode(HttpStatus.SC_NOT_FOUND)
                .extract().response().body().as(GenericResponse.class);

        Assertions.assertEquals("Pet not found", response.getMessage());
    }

    @Test
    public void testCadastrarPetComSucesso() {
        PetModel pet = PetDataFactory.validPet();

        PetResponse response = petClient.postPet(pet)
            .then()
                .statusCode(200)
                .extract().response().body().as(PetResponse.class);

        Assertions.assertEquals(pet.getName(), response.getName());
    }
}
