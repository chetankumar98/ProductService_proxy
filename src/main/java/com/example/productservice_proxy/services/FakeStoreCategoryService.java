package com.example.productservice_proxy.services;

import com.example.productservice_proxy.clients.fakestore.client.FakeStoreClient;
import com.example.productservice_proxy.clients.fakestore.dto.FakeStoreProductDTO;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FakeStoreCategoryService {
    RestTemplateBuilder restTemplateBuilder;
    FakeStoreClient fakeStoreClient;
    public FakeStoreCategoryService(RestTemplateBuilder restTemplateBuilder,FakeStoreClient fakeStoreClient){
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
    }

    public List<String> getAllCategories() {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<String[]> categories =
//                restTemplate
//                        .getForEntity("https://fakestoreapi.com/products/categories", String[].class);
        String[] categories = fakeStoreClient.getAllCategories();
        return  Arrays.asList(categories);
    }

    public List<Product> productsInCategory(String category) {
        FakeStoreProductDTO[] allProductsinCategory = fakeStoreClient.getAllProductsinCategory(category);
        List<Product> answer = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO:allProductsinCategory){
            Product product = new Product();
            product.setId(fakeStoreProductDTO.getId());
            product.setTitle(fakeStoreProductDTO.getTitle());
            product.setPrice(fakeStoreProductDTO.getPrice());
            Categories category1 = new Categories();
            category1.setName(fakeStoreProductDTO.getCategory());
            product.setCategory(category1);
            product.setImageURL(fakeStoreProductDTO.getImage());
            product.setImageURL(fakeStoreProductDTO.getImage());
            answer.add(product);
        }
        return answer;
    }
}
