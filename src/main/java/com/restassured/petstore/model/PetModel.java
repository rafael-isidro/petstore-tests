package com.restassured.petstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetModel {

    private Long id;
    private int idCategory;
    private String nameCategory;
    private String name;
    private String photoUrls;
    private int tagsId;
    private String tagsName;
    private String status;

}
