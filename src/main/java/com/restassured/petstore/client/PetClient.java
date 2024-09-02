package com.restassured.petstore.client;

import com.restassured.petstore.data.factory.PetDataFactory;
import com.restassured.petstore.model.PetModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class PetClient extends BaseClient {

    private final String INVALID_STATUS = "1234 abc";
    private final String VALID_ID = "9223372036854030000";
    private final Long INVALID_ID = -111L;
    private final String NON_EXISTING_ID = "100000000002111";
    private final String PET ="/pet";
    private final String PET_BY_ID ="/pet/{id}";

    public Response getPetById() {
        return
            given()
                .spec(super.set())
                .pathParams("id", VALID_ID)
            .when()
                .get(PET_BY_ID);
    }

    public Response getPetByNonExistingId() {
        return
            given()
                .spec(super.set())
                .pathParams("id", NON_EXISTING_ID)
            .when()
                .get(PET_BY_ID);
    }

    public Response postPet(PetModel pet) {
        return
            given()
                .spec(super.set())
                .contentType(ContentType.JSON)
                .pathParams("id", VALID_ID)
                .body(pet)
            .when()
                .post(PET);
    }

    public Response updatePet(PetModel pet) {
        pet.setId(Long.valueOf(VALID_ID));
        return
            given()
                .spec(super.set())
                .contentType(ContentType.JSON)
                .body(pet)
            .when()
                .put(PET);
    }

    public Response updatePetByNonExistingId(PetModel pet) {
        pet.setId(Long.valueOf(NON_EXISTING_ID));
        return
            given()
                .spec(super.set())
                .contentType(ContentType.JSON)
                .body(pet)
            .when()
                .put(PET);
    }

    public Response updatePetByInvalidId(PetModel pet) {
        pet.setId(Long.valueOf(INVALID_ID));

        return
            given()
                .spec(super.set())
                .contentType(ContentType.JSON)
                .body(pet)
            .when()
                .put(PET);
    }

    public Response updatePetInvalidStatus(PetModel pet) {
        pet.setStatus(INVALID_STATUS);

        return
            given()
                .spec(super.set())
                .contentType(ContentType.JSON)
                .body(pet)
            .when()
                .put(PET);
    }
}
