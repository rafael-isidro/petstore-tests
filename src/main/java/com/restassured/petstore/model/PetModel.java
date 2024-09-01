package com.restassured.petstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetModel {

    private Long id;
    private String name;
    private List<TagModel> tags = new ArrayList<>();
    private CategoryModel category = new CategoryModel();
    private List<String> photoUrls = new ArrayList<>();
    private String status;

}
