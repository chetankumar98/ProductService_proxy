package com.example.productservice_proxy.clients.fakestore.client;

import com.example.productservice_proxy.clients.IClientProductDTO;
import com.example.productservice_proxy.clients.fakestore.dto.FakeStoreCartDto;
import com.example.productservice_proxy.clients.fakestore.dto.FakeStoreProductDTO;
import com.example.productservice_proxy.dtos.ProductDTO;
import com.example.productservice_proxy.models.Cart;
import com.example.productservice_proxy.models.Product;
import jakarta.annotation.Nullable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@Component
public class FakeStoreClient {
    RestTemplateBuilder restTemplateBuilder;
    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<FakeStoreProductDTO> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> l = restTemplate.getForEntity
                ("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);
        return Arrays.asList(l.getBody());
    }

    public FakeStoreProductDTO getSingleProduct(long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity  = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                FakeStoreProductDTO.class, productId);
        return fakeStoreProductDTOResponseEntity.getBody();
    }

    public IClientProductDTO addNewProduct(IClientProductDTO productDTO){
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.postForEntity("https://fakestoreapi.com/products",productDTO,ProductDTO.class);
        return productDTO;
    }

    public FakeStoreProductDTO updateProduct(Long productID, FakeStoreProductDTO fakeStoreProductDTO){
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
                requestForEntity(HttpMethod.PATCH, "https://fakestoreapi.com/products/{id}",fakeStoreProductDTO,
                        FakeStoreProductDTO.class,productID);
        return fakeStoreProductDTOResponseEntity.getBody();
    }

    public String[] getAllCategories() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String[]> categories =
                restTemplate
                        .getForEntity("https://fakestoreapi.com/products/categories", String[].class);
        return categories.getBody();
    }
    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                  Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);

        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    public FakeStoreProductDTO[] getAllProductsinCategory(String category) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> allProductsinCategory =
                restTemplate
                        .getForEntity("https://fakestoreapi.com/products/category/{category}", FakeStoreProductDTO[].class,category);
        return allProductsinCategory.getBody();
    }

    public FakeStoreCartDto getSingleCart(long CartId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreCartDto> responseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/carts/{CartId}",FakeStoreCartDto.class,CartId);
        return responseEntity.getBody();
    }

    public List<FakeStoreCartDto> getAllCarts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreCartDto[]> fakeStoreCartDtos = restTemplate.getForEntity(
                "https://fakestoreapi.com/carts",FakeStoreCartDto[].class);
        return Arrays.asList(fakeStoreCartDtos.getBody());
    }
    public List<FakeStoreCartDto> getCartsinDateRange(String startDate, String endDate){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreCartDto[]> CartsinRange = restTemplate.getForEntity(
                "https://fakestoreapi.com/carts?startdate={stratDate}&enddate={endDate}",FakeStoreCartDto[].class);
        return Arrays.asList(CartsinRange.getBody());
    }
    public List<FakeStoreCartDto> getUserCarts(long userId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreCartDto[]> carts = restTemplate.getForEntity(
                "https://fakestoreapi.com/carts/user/{userId}",FakeStoreCartDto[].class);
        return Arrays.asList(carts.getBody());
    }
    public Cart addNewProducttoCart(Cart cart){
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.postForEntity("https://fakestoreapi.com/carts",cart,FakeStoreCartDto.class);
        return cart;
    }
}
