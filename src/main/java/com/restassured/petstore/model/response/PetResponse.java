package com.restassured.petstore.model.response;

import com.restassured.petstore.model.CategoryModel;
import com.restassured.petstore.model.TagModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetResponse {

    private Long id;
    private CategoryModel category;
    private String name;
    private List<String> photoUrls;
    private List<TagModel> tags;
    private String status;

}
