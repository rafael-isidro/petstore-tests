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
                .statusCode(HttpStatus.SC_OK)
                .extract().response().body().as(PetResponse.class);

        Assertions.assertEquals(pet.getName(), response.getName());
    }

    //BUG: Ao tentar cadastrar pet com nome em branco, é retornado código e body de sucesso ao invés do status 400 e mensagem de erro.
    @Test
    public void testTentarCadastrarPetComNomeVazio() {
        PetModel pet = PetDataFactory.petEmptyName();

        GenericResponse response = petClient.postPet(pet)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().response().body().as(GenericResponse.class);

        Assertions.assertEquals("name is required", response.getMessage());
    }

    //BUG: Ao tentar cadastrar pet com id já existente retorna codigo e mensagem de sucesso.
    @Test
    public void testTentarCadastrarPetComIdExistente() {
        PetModel pet = PetDataFactory.petExistingId();

        GenericResponse response = petClient.postPet(pet)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().response().body().as(GenericResponse.class);

        Assertions.assertEquals("ID already exists", response.getMessage());
    }

    @Test
    public void testAtualizarPetComSucesso() {
        PetModel pet = PetDataFactory.validPet();

        PetResponse response = petClient.updatePet(pet)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().body().as(PetResponse.class);

        Assertions.assertEquals(pet.getName(), response.getName());
    }

    //BUG: Ao tentar atualizar pet com ID não existente ele cria um novo
    @Test
    public void testTentarAtualizarPetComIdInexistente() {
        PetModel pet = PetDataFactory.validPet();

        GenericResponse response = petClient.updatePetByNonExistingId(pet)
            .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract().response().body().as(GenericResponse.class);

        Assertions.assertEquals("Pet not found", response.getMessage());
    }

    //BUG: Ao tentar atualizar pet com ID inválido ele cria um novo
    @Test
    public void testTentarAtualizarPetComIdInvalido() {
        PetModel pet = PetDataFactory.validPet();

        GenericResponse response = petClient.updatePetByInvalidId(pet)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().response().body().as(GenericResponse.class);

        Assertions.assertEquals("Invalid pet ID", response.getMessage());
    }

    //BUG: Ao tentar atualizar pet com valor em status diferente do enum [ available, pending, sold ], cria um novo
    @Test
    public void testTentarAtualizarPetComStatusInvalido() {
        PetModel pet = PetDataFactory.validPet();

        GenericResponse response = petClient.updatePetInvalidStatus(pet)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().response().body().as(GenericResponse.class);

        Assertions.assertEquals("Invalid status", response.getMessage());
    }

}
