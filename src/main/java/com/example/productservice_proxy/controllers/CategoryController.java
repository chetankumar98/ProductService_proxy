package com.example.productservice_proxy.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("products/categories")
public class CategoryController {
    @GetMapping("")
    public String getAllCategories(){
        return "Getting all Categories";
    }
    @GetMapping("{CategoryId}")
    public String getProductsinCategory(@PathVariable("CategoryId") Long CategoryId){
        return "Getting all products in Category: "+ CategoryId;
    }
}
