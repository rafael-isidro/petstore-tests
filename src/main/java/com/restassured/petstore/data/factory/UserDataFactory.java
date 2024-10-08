package com.restassured.petstore.data.factory;

import com.restassured.petstore.model.UserModel;
import net.datafaker.Faker;

public class UserDataFactory {

    private static Faker faker = new Faker();

    public static UserModel validUser() {
        return newUser();
    }

    public static UserModel userNullUsername() {
        UserModel user = newUser();
        user.setUsername(null);
        return user;
    }

    public static UserModel userNullPassword() {
        UserModel user = newUser();
        user.setPassword(null);
        return user;
    }

    public static UserModel userEmptyFields() {
        UserModel user = new UserModel();

        user.setId(faker.number().numberBetween(10000L, 25000L));
        user.setUsername("");
        user.setFirstName("");
        user.setLastName("");
        user.setEmail("");
        user.setPassword("");
        user.setPhone("");
        user.setUserStatus(0);

        return user;
    }

    private static UserModel newUser() {
        UserModel user = new UserModel();

        user.setId(faker.number().numberBetween(10000L, 25000L));
        user.setUsername(faker.name().username());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        user.setPhone(faker.phoneNumber().phoneNumber());
        user.setUserStatus(faker.number().numberBetween(0, 1));

        return user;
    }

    public static UserModel userInvalidPhone() {
        UserModel user = newUser();
        user.setPhone("abcd");

        return user;
    }

}
