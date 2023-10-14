package com.example.productservice_proxy.controllers;


import com.example.productservice_proxy.dtos.ProductDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("")
    public String getAllProduct(){
        return "Getting All the Products";
    }
    @GetMapping("/{productId}")
    public String getSingleProduct(@PathVariable("productId") Long productId){
        return "Returning Single product with productID : "+productId;
    }
    @PostMapping()
    public String addNewProduct(@RequestBody ProductDTO productDTO ){
        return "Adding New Product" + productDTO;
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
