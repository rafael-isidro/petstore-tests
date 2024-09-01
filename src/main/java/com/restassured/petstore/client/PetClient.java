package com.restassured.petstore.client;

import com.restassured.petstore.data.factory.PetDataFactory;
import com.restassured.petstore.model.PetModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class PetClient extends BaseClient {

    private static final String VALID_ID = "9223372036854030000";
    private static final String NON_EXISTING_ID = "100000000001111";
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

    public Response postPet(PetModel pet) {
        return
            given()
                .spec(super.set())
                .contentType(ContentType.JSON)
                .body(pet)
            .when()
                .post(PET);
    }

    public Response getPetByNonExistingId() {
        return
            given()
                .spec(super.set())
                .pathParams("id", NON_EXISTING_ID)
                .when()
                .get(PET_BY_ID);
    }
}
