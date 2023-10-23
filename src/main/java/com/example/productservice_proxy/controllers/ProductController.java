package com.example.productservice_proxy.controllers;


import com.example.productservice_proxy.dtos.ProductDTO;
import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.services.IProductService;
import com.example.productservice_proxy.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    IProductService productService;

    public  ProductController(IProductService productService){
        this.productService = productService;
    }

    @GetMapping("")
    public String getAllProduct(){
        return "Getting All the Products";
    }

    @GetMapping("/{productId}")
    public Product getSingleProduct(@PathVariable("productId") Long productId){
        Product product =  this.productService.getSingleProduct(productId);
        return product;
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
