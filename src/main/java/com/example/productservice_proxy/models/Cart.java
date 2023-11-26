package com.example.productservice_proxy.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class Cart extends BaseModels{
    private long userId;
    private List<HashMap<String, Long>> products;
//    private String date;
    private long __v;
}
