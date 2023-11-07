package com.example.productservice_proxy.clients.fakestore.client;

import com.example.productservice_proxy.clients.fakestore.dto.FakeStoreProductDTO;
import com.example.productservice_proxy.dtos.ProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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
        ResponseEntity<FakeStoreProductDTO[]> l =
                restTemplate
                        .getForEntity("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);
        return Arrays.asList(l.getBody());

    }
}
