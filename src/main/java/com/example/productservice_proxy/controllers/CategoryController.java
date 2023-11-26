package com.example.productservice_proxy.controllers;


import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.services.FakeStoreCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("products/")
public class CategoryController {
    FakeStoreCategoryService fakeStoreCategoryService;
    public CategoryController(FakeStoreCategoryService fakeStoreCategoryService){
        this.fakeStoreCategoryService = fakeStoreCategoryService;
    }
    @GetMapping("categories")
    public ResponseEntity<List<String>> getAllCategories(){
        List<String> list = fakeStoreCategoryService.getAllCategories();
        ResponseEntity<List<String>> responseEntity = new ResponseEntity<>(list,HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("category/{Category}")
    public ResponseEntity<List<Product>> getProductsinCategory(@PathVariable("Category") String category) throws Exception {
        List<Product> allProductsinCategory = fakeStoreCategoryService.productsInCategory(category);
        ResponseEntity responseEntity = new ResponseEntity(allProductsinCategory,HttpStatus.OK);
        return responseEntity;
    }

}
