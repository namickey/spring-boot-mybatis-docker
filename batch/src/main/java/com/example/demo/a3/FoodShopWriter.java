package com.example.demo.a3;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FoodShopWriter implements ItemWriter<Item> {
    @Override
    public void write(List<? extends Item> list) throws Exception {
        for (Item item: list) {
            System.out.println(item.getName());
        }
    }
}
