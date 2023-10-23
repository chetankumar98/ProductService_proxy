package com.example.productservice_proxy.services;

import com.example.productservice_proxy.dtos.ProductDTO;
import com.example.productservice_proxy.dtos.RatingDTO;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Service
public class ProductService implements IProductService{
    private RestTemplateBuilder restTemplateBuilder;
    public ProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public String getAllProducts(){
        return null;
    }
    @Override
    public Product getSingleProduct(Long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ProductDTO productDTO = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                ProductDTO.class,productId).getBody();

        Product product = getProduct(productDTO);

        return product;
    }



    @Override
    public String addNewProduct(){
        return null;
    }


    @Override
    public String updateProduct(){
        return null;
    }


    @Override
    public String PatchProduct(){
        return null;
    }

    @Override
    public String deleteProduct(){
        return null;
    }

    private Product getProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImageURL(productDTO.getImage());
        Categories category = new Categories();
        category.setName(productDTO.getCategory());
        product.setCategory(category);
        return product;
    }
}
