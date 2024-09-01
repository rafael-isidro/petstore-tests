package com.restassured.petstore.tests.pet;

import com.restassured.petstore.client.UserClient;
import com.restassured.petstore.data.factory.UserDataFactory;
import com.restassured.petstore.model.UserModel;
import com.restassured.petstore.model.response.UserResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    static UserClient userClient = new UserClient();
    static UserModel user = UserDataFactory.validUser();

    @BeforeEach
    void setUp() {
        userClient.postUser(user)
            .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testBuscarUserPorUsernameComSucesso() {
        userClient.getUserByUsername(user.getUsername())
            .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testTentarBuscarUserPorUsernameNaoCadastrado() {
        String message = userClient.getUserByUsernameNaoCadastrado()
            .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract().path("message");

        Assertions.assertEquals("User not found", message);
    }

    @Test
    public void testCadastrarUsuarioComSucesso() {
        UserModel user = UserDataFactory.validUser();

        UserResponse response = userClient.postUser(user)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().body().as(UserResponse.class);

        Assertions.assertEquals(user.getId().toString(), response.getMessage());
    }

    // BUG: O sistema, ao cadastrar usuario informando Username como null, retorna status 200 diferente do comportamento esperado.
    @Test
    public void testTentarCadastrarUsuarioComUsernameNulo() {
        UserModel user = UserDataFactory.userNullUsername();

        UserResponse response = userClient.postUser(user)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .extract().response().body().as(UserResponse.class);

        Assertions.assertEquals("Please enter a valid username", response.getMessage());
        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getCode());
    }

    // BUG: O sistema, ao cadastrar usuario informando campos vazios, retorna status 200 diferente do comportamento esperado.
    @Test
    public void testTentarCadastrarUsuarioComDadosVazios() {
        UserModel user = UserDataFactory.userEmptyFields();

        UserResponse response = userClient.postUser(user)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().response().body().as(UserResponse.class);

        Assertions.assertEquals("All fields are required", response.getMessage());
        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getCode());
    }

    // BUG: O sistema, ao cadastrar usuario informando numero de telefone invalido, retorna status 200 diferente do comportamento esperado.
    @Test
    public void testTentarCadastrarUsuarioComTelefoneInvalido() {
        UserModel user = UserDataFactory.userInvalidPhone();

        UserResponse response = userClient.postUser(user)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().response().body().as(UserResponse.class);

        Assertions.assertEquals("Please enter a valid phone number", response.getMessage());
        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getCode());
    }

    @Test
    public void testEditarUsuarioComSucesso() {
        UserModel newUser = UserDataFactory.validUser();
        newUser.setId(user.getId());

        UserResponse response = userClient.putUser(user, newUser)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().body().as(UserResponse.class);

        Assertions.assertEquals(newUser.getId().toString(), response.getMessage());
        Assertions.assertEquals(HttpStatus.SC_OK, response.getCode());
    }

    // BUG: O sistema, ao editar usuario informando Username como null, retorna status 200 diferente do comportamento esperado.
    @Test
    public void testTentarEditarUsuarioComUsernameNulo() {
        UserModel newUser = UserDataFactory.userNullUsername();
        newUser.setId(user.getId());

        UserResponse response = userClient.putUser(user, newUser)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().response().body().as(UserResponse.class);

        Assertions.assertEquals("Please enter a valid username", response.getMessage());
        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getCode());
    }

    // BUG: O sistema, ao editar usuario informando campos vazios, retorna status 200 diferente do comportamento esperado.
    @Test
    public void testTentarEditarUsuarioComDadosVazios() {
        UserModel newUser = UserDataFactory.userEmptyFields();

        UserResponse response = userClient.putUser(user, newUser)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().response().body().as(UserResponse.class);

        Assertions.assertEquals("All fields are required", response.getMessage());
        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getCode());
    }

    @Test
    public void testExcluirUsuarioComSucesso() {
        UserResponse response = userClient.deleteUser(user)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().body().as(UserResponse.class);

        Assertions.assertEquals(user.getUsername(), response.getMessage());
        Assertions.assertEquals(HttpStatus.SC_OK, response.getCode());
    }

    @Test
    public void testTentarExcluirUsuarioComUsernameNaoCadastrado() {
       userClient.deleteUserByUsernameNaoCadastrado()
            .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);

    }
}
