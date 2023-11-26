package com.example.productservice_proxy.services;

import com.example.productservice_proxy.clients.fakestore.client.FakeStoreClient;
import com.example.productservice_proxy.clients.fakestore.dto.FakeStoreCartDto;
import com.example.productservice_proxy.models.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FakeStoreCartService {
    FakeStoreClient fakeStoreClient;
    public FakeStoreCartService(FakeStoreClient fakeStoreClient){
        this.fakeStoreClient = fakeStoreClient;
    }
    public Cart getSingleCart(long cartId){
        FakeStoreCartDto fakeStoreCartDto = fakeStoreClient.getSingleCart(cartId);
        Cart cart = getCart(fakeStoreCartDto);
        return cart;
    }

    public List<Cart> getAllCarts(){
        List<FakeStoreCartDto> fakeStoreCartDtos = fakeStoreClient.getAllCarts();
        return getListofCarts(fakeStoreCartDtos);
    }
    public List<Cart> getCartsinDateRange(String startDate, String endDate){
        List<FakeStoreCartDto> fakeStoreCartDtos = fakeStoreClient.getCartsinDateRange(startDate,endDate);
        return getListofCarts(fakeStoreCartDtos);
    }
    public List<Cart> getUserCarts(long userId){
        List<FakeStoreCartDto> fakeStoreCartDtos= fakeStoreClient.getUserCarts(userId);
        return getListofCarts(fakeStoreCartDtos);
    }
    public Cart addNewProducttoCart(Cart cart){
        Cart cart1= fakeStoreClient.addNewProducttoCart(cart);
        return cart1;
    }
    private List<Cart> getListofCarts(List<FakeStoreCartDto> fakeStoreCartDtos){
        List<Cart> carts = new ArrayList<>();
        for(FakeStoreCartDto fakeStoreCartDto : fakeStoreCartDtos){
            carts.add(getCart(fakeStoreCartDto));
        }
        return carts;
    }
    private Cart getCart(FakeStoreCartDto fakeStoreCartDto) {
        Cart cart = new Cart();
        cart.setId(fakeStoreCartDto.getId());
        cart.setCreatedAt(fakeStoreCartDto.getDate());
        cart.setUserId(fakeStoreCartDto.getUserId());
        cart.set__v(fakeStoreCartDto.get__v());
        List<HashMap <String,Long>> cProducts =new ArrayList<>();
        cProducts.addAll(fakeStoreCartDto.getProducts());
        cart.setProducts(cProducts);
        return cart;
    }
}
