package com.example.productservice_proxy.clients.fakestore.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@ToString
public class FakeStoreCartDto {
    private long id;
    private long userId;
    private Date date;
    private List<HashMap<String, Long>> products;
    private long __v;
}
