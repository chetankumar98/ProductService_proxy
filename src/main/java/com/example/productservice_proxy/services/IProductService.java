package com.example.productservice_proxy.services;

import com.example.productservice_proxy.models.Product;

public interface IProductService {
    String getAllProducts();

    Product getSingleProduct(Long productId);

    String addNewProduct();

    String updateProduct();

    String PatchProduct();

    String deleteProduct();
}
