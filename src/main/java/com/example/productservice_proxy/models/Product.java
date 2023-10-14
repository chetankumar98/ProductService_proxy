package com.example.productservice_proxy.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModels{
    private String title;
    private double price;
    private String description;
    private Categories category;
    private String imageURL;
}
