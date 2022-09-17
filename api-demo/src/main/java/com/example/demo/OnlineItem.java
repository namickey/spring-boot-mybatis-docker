package com.example.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "item.shop", havingValue ="false" , matchIfMissing = true)
public class OnlineItem implements Item{
    @Override
    public String getName() {
        return "Online item";
    }
}
