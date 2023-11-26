package com.example.productservice_proxy.controllers;

import com.example.productservice_proxy.models.Cart;
import com.example.productservice_proxy.services.FakeStoreCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/carts")
@RestController
public class CartController {
    FakeStoreCartService fakeStoreCartService;
    public CartController(FakeStoreCartService fakeStoreCartService){
        this. fakeStoreCartService = fakeStoreCartService;
    }
    @GetMapping("")
    public ResponseEntity<List<Cart>> getAllCarts(){
        List<Cart> carts = fakeStoreCartService.getAllCarts();
        ResponseEntity responseEntity = new ResponseEntity(carts,HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("/{CartId}")
    public ResponseEntity<Cart> getSingleCart(@PathVariable long CartId){
        Cart cart = fakeStoreCartService.getSingleCart(CartId);
        ResponseEntity<Cart> responseEntity = new ResponseEntity<>(cart, HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("?startdate={StartDate}&enddate={EndDate}")
    public ResponseEntity<List<Cart>> GetCartsinDateRange(@PathVariable String StartDate, @PathVariable String EndDate){
        List<Cart> cart = fakeStoreCartService.getCartsinDateRange(StartDate,EndDate);
        ResponseEntity<List<Cart>> responseEntity = new ResponseEntity<>(cart,HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("/user/{UserId}")
    public ResponseEntity<List<Cart>> getUserCarts(@PathVariable long UserId){
        List<Cart> carts = fakeStoreCartService.getUserCarts(UserId);
        ResponseEntity<List<Cart>> responseEntity = new ResponseEntity<>(carts,HttpStatus.OK);
        return responseEntity;
    }
    @PostMapping("")
    public ResponseEntity<Cart> addNewProducttoCart(@RequestBody Cart cart){
        Cart cart1 = fakeStoreCartService.addNewProducttoCart(cart);
        ResponseEntity responseEntity = new ResponseEntity(cart1,HttpStatus.OK);
        return responseEntity;
    }
    @PutMapping("/{CartId}")
    @PatchMapping("/{CartId}")
    public ResponseEntity updateProduct(@PathVariable long CartId,@RequestBody Cart cart){
        
    }
    @DeleteMapping("/{cartId}")
    public String deleteCart(@PathVariable long cartId){
        return null;
    }
}
