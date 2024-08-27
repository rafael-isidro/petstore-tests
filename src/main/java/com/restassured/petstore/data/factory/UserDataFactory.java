package com.restassured.petstore.data.factory;

import com.restassured.petstore.model.UserModel;
import net.datafaker.Faker;

public class UserDataFactory {

    private static Faker faker = new Faker();

    public static UserModel validUser() {
        return newUser();
    }

    public static UserModel newUser() {
        UserModel user = new UserModel();

        user.setId(faker.number().numberBetween(10000L, 15000L));
        user.setUsername(faker.name().username());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        user.setPhone(faker.phoneNumber().phoneNumber());
        user.setUserStatus(faker.number().numberBetween(0, 1));

        return user;
    }
}
