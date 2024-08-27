package com.restassured.petstore.tests.pet;

import com.restassured.petstore.client.UserClient;
import com.restassured.petstore.data.factory.UserDataFactory;
import com.restassured.petstore.model.UserModel;
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
                .statusCode(200);
    }

    @Test
    public void testBuscarUserPorUsernameComSucesso() {
        userClient.getUserByUsername(user.getUsername())
            .then()
                .statusCode(200);
    }

    @Test
    public void testTentarBuscarUserPorUsernameNaoCadastrado() {
        String message = userClient.getUserByUsernameNaoCadastrado()
            .then()
                .statusCode(404)
                .extract().path("message");

        Assertions.assertEquals("User not found", message);
    }

    @Test
    public void testCadastrarUsuarioComSucesso() {
        UserModel user = UserDataFactory.validUser();

        String message = userClient.postUser(user)
            .then()
                .statusCode(200)
                .extract().path("message");

        Assertions.assertEquals(user.getId().toString(), message);
    }

    // BUG: O sistema, ao cadastrar usuario informando Username como null, retorna status 200 diferente do comportamento esperado.
    @Test
    public void testTentarCadastrarUsuarioComUsernameNulo() {
        UserModel user = UserDataFactory.userNullUsername();

        String message = userClient.postUser(user)
            .then()
                .statusCode(400)
                .extract().path("message");

        Assertions.assertEquals("Please enter a valid username", message);
    }

    // BUG: O sistema, ao cadastrar usuario informando campos vazios, retorna status 200 diferente do comportamento esperado.
    @Test
    public void testTentarCadastrarUsuarioComDadosVazios() {
        UserModel user = UserDataFactory.userEmptyFields();

        String message = userClient.postUser(user)
            .then()
                .statusCode(400)
                .extract().path("message");

        Assertions.assertEquals("All fields are required", message);
    }

    // BUG: O sistema, ao cadastrar usuario informando numero de telefone invalido, retorna status 200 diferente do comportamento esperado.
    @Test
    public void testTentarCadastrarUsuarioComTelefoneInvalido() {
        UserModel user = UserDataFactory.userInvalidPhone();

        String message = userClient.postUser(user)
            .then()
                .statusCode(400)
                .extract().path("message");

        Assertions.assertEquals("Please enter a valid phone number", message);
    }

    @Test
    public void testEditarUsuarioComSucesso() {
        UserModel newUser = UserDataFactory.validUser();
        newUser.setId(user.getId());

        String message = userClient.putUser(user, newUser)
            .then()
                .statusCode(200)
                .extract().path("message");

        Assertions.assertEquals(newUser.getId().toString(), message);
    }

    // BUG: O sistema, ao editar usuario informando Username como null, retorna status 200 diferente do comportamento esperado.
    @Test
    public void testTentarEditarUsuarioComUsernameNulo() {
        UserModel newUser = UserDataFactory.userNullUsername();
        newUser.setId(user.getId());

        String message = userClient.putUser(user, newUser)
            .then()
                .statusCode(400)
                .extract().path("message");

        Assertions.assertEquals("Please enter a valid username", message);
    }

    // BUG: O sistema, ao editar usuario informando campos vazios, retorna status 200 diferente do comportamento esperado.
    @Test
    public void testTentarEditarUsuarioComDadosVazios() {
        UserModel newUser = UserDataFactory.userEmptyFields();

        String message = userClient.putUser(user, newUser)
            .then()
                .statusCode(400)
                .extract().path("message");

        Assertions.assertEquals("All fields are required", message);
    }

    @Test
    public void testExcluirUsuarioComSucesso() {
        String message = userClient.deleteUser(user)
            .then()
                .statusCode(200)
                .extract().path("message");

        Assertions.assertEquals(user.getUsername(), message);
    }

    @Test
    public void testTentarExcluirUsuarioComUsernameNaoCadastrado() {
        userClient.deleteUserByUsernameNaoCadastrado()
            .then()
                .statusCode(404);
    }
}
