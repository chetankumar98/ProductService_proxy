package com.example.productservice_proxy.services;

import com.example.productservice_proxy.clients.IClientProductDTO;
import com.example.productservice_proxy.dtos.ProductDTO;
import com.example.productservice_proxy.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getSingleProduct(Long productId);

    Product addNewProduct(IClientProductDTO productDTO);

    Product updateProduct(Long productID, Product product);


    String deleteProduct();
}
