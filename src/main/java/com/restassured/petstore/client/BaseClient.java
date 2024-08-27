package com.restassured.petstore.client;

import com.restassured.petstore.utils.Manipulation;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.specification.RequestSpecification;

public class BaseClient {
    final String BASE_URI = Manipulation.getProp().getProperty("base.uri");

    public RequestSpecification set() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setConfig(RestAssured.config().logConfig(
                        LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .build()
                ;
    }
}
