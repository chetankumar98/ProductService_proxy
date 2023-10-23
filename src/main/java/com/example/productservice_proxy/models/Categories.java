package com.example.productservice_proxy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
//@AllArgsConstructor
public class Categories extends BaseModels {
    private String name;
    private String description;
    private List<Product> productList;

}
