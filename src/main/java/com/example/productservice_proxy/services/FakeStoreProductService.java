package com.example.productservice_proxy.services;

import com.example.productservice_proxy.clients.IClientProductDTO;
import com.example.productservice_proxy.clients.fakestore.client.FakeStoreClient;
import com.example.productservice_proxy.clients.fakestore.dto.FakeStoreProductDTO;
import com.example.productservice_proxy.dtos.ProductDTO;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import jakarta.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


@Service
public class FakeStoreProductService implements IProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder,FakeStoreClient fakeStoreClient){
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
    }

    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                  Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);

        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
    @Override
    public List<Product> getAllProducts(){
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<ProductDTO[]> productDtos =
//                restTemplate
//                        .getForEntity("https://fakestoreapi.com/products", ProductDTO[].class);
        List<FakeStoreProductDTO> fakeStoreProductDTOS = fakeStoreClient.getAllProducts();

        List<Product> answer = new ArrayList<>();

        for (FakeStoreProductDTO productDto: fakeStoreProductDTOS) {
            Product product = new Product();
            product.setId(productDto.getId());
            product.setTitle(productDto.getTitle());
            product.setPrice(productDto.getPrice());
            Categories category = new Categories();
            category.setName(productDto.getCategory());
            product.setCategory(category);
            product.setImageURL(productDto.getImage());
            product.setImageURL(productDto.getImage());
            answer.add(product);
        }
        return answer;
    }
    @Override
    public Product getSingleProduct(Long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> productDTO = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                FakeStoreProductDTO.class, productId);

        Product product = getProduct(productDTO.getBody());

        return product;
    }



    @Override
    public Product addNewProduct(IClientProductDTO productDTO){
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.postForEntity("https://fakestoreapi.com/products",productDTO,ProductDTO.class);
        Product product = getProduct((FakeStoreProductDTO) productDTO);
        return product;
    }


    @Override
    public Product updateProduct(Long productID, Product product){
        RestTemplate restTemplate = restTemplateBuilder.build();

        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setImage(product.getImageURL());
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());

        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
                requestForEntity(HttpMethod.PATCH, "https://fakestoreapi.com/products/{id}",fakeStoreProductDTO,
                        FakeStoreProductDTO.class,productID);

        FakeStoreProductDTO fakeStoreProductDTO1 = fakeStoreProductDTOResponseEntity.getBody();
        return getProduct(fakeStoreProductDTO1);

    }

    @Override
    public String deleteProduct(){
        return null;
    }

    private Product getProduct(FakeStoreProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImageURL(productDTO.getImage());
        Categories category = new Categories();
        category.setName(productDTO.getCategory());
        product.setCategory(category);
        return product;
    }
}
