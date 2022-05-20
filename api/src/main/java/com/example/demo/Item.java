package com.example.demo;

import lombok.Data;

@Data
public class Item {

    private String name;
    private Integer price;

    public Item(String name, Integer price){
        this.name = name;
        this.price = price;
    };

}
