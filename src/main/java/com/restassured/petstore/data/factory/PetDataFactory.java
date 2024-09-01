package com.restassured.petstore.data.factory;

import com.restassured.petstore.model.PetModel;
import net.datafaker.Faker;

public class PetDataFactory {

    static Faker faker = new Faker();

    public static PetModel validPet() {
        return newPet();
    }

    public static PetModel newPet() {
        PetModel pet = new PetModel();

        pet.setId(faker.number().numberBetween(2000000L, 3000000L));
        pet.setName(faker.name().firstName());
        pet.getTags().forEach(tagModel -> {
            tagModel.setIdTag(faker.number().numberBetween(2000000L, 3000000L));
            tagModel.setNameTag(faker.name().firstName());
        });
        pet.getCategory().setIdCategory(faker.number().numberBetween(2000000L, 3000000L));
        pet.getCategory().setNameCategory(faker.animal().species());

        pet.getPhotoUrls().add(faker.internet().image());
        pet.setStatus("available");

        return pet;
    }
}
