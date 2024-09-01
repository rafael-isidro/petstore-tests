package com.restassured.petstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel {

    @JsonProperty("id")
    private Long idCategory;

    @JsonProperty("name")
    private String nameCategory;
}
