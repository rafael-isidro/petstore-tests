package com.restassured.petstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagModel {

    @JsonProperty("id")
    private Long idTag;

    @JsonProperty("name")
    private String nameTag;
}
