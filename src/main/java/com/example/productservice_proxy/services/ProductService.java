package com.example.productservice_proxy.services;

import com.example.productservice_proxy.dtos.ProductDTO;
import com.example.productservice_proxy.dtos.RatingDTO;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService implements IProductService{
    private RestTemplateBuilder restTemplateBuilder;
    public ProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public List<Product> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDTO[]> productDTOs = restTemplate.getForEntity("https://fakestoreapi.com/products",
                ProductDTO[].class);

        List<Product> allproducts = new ArrayList<>();
        for (ProductDTO productDTO : productDTOs.getBody()){
            allproducts.add(getProduct(productDTO));
        }

        return allproducts;
    }
    @Override
    public Product getSingleProduct(Long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDTO> productDTO = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                ProductDTO.class,productId);

        Product product = getProduct(productDTO.getBody());

        return product;
    }



    @Override
    public Product addNewProduct(ProductDTO productDTO){
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.postForEntity("https://fakestoreapi.com/products",productDTO,ProductDTO.class);
        Product product = getProduct(productDTO);
        return product;
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
