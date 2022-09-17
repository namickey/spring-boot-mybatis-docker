package com.example.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "item.shop")
public class ShopItem implements Item {
    @Override
    public String getName() {
        return "Shop item";
    }
}
