package com.example.productservice_proxy.controllers;


import com.example.productservice_proxy.dtos.ProductDTO;
import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.services.IProductService;
import com.example.productservice_proxy.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    IProductService productService;

    public  ProductController(IProductService productService){
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(this.productService.getAllProducts(),HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId){
        try {
            MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
            headers.add("Accept","application/json");
            headers.add("Content-Type","application/json");
            headers.add("auth-token","heyaccess");
            Product product = this.productService.getSingleProduct(productId);
            if(productId < 1){
                throw new IllegalArgumentException("ProductID not Found");
            }
            ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, headers, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e){
            ResponseEntity<Product> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDTO productDTO ){
        Product product = this.productService.addNewProduct(productDTO);
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(product,HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId){
        return "updating product "+productId;
    }

    @PatchMapping("/{productId}")
    public String PatchProduct(@PathVariable("productId") Long productId){
        return "patching product "+productId;
    }
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        return "Deleting Single product with productID : "+productId;
    }

}
