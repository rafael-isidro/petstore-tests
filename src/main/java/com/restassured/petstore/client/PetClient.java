package com.restassured.petstore.client;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class PetClient extends BaseClient {

    private static final String VALID_ID = "1";
    private final String PET_BY_ID ="/pet/{id}";

    public Response getPetById() {
        return
            given()
                .spec(super.set())
                .pathParams("id", VALID_ID)
            .when()
                .get(PET_BY_ID);
    }
}
