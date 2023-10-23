package com.example.productservice_proxy.dtos;

import com.example.productservice_proxy.models.Categories;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDTO {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private RatingDTO rating;
}
