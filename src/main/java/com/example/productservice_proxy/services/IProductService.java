package com.example.productservice_proxy.services;

public interface IProductService {
    String getAllProducts();

    String getSingleProduct();

    String addNewProduct();

    String updateProduct();

    String PatchProduct();

    String deleteProduct();
}
