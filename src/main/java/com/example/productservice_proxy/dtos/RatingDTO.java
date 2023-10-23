package com.example.productservice_proxy.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RatingDTO {
    private float rate;
    private int count;
}
