package com.example.productservice_proxy.clients.fakestore.dto;


import com.example.productservice_proxy.clients.IClientProductDTO;
import com.example.productservice_proxy.dtos.RatingDTO;
import com.example.productservice_proxy.models.Categories;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreProductDTO implements IClientProductDTO {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private RatingDTO rating;
}

