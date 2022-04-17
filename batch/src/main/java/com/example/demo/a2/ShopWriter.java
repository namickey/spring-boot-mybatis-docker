package com.example.demo.a2;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShopWriter implements ItemWriter<Item> {
    @Override
    public void write(List<? extends Item> list) throws Exception {
        for (Item item: list) {
            System.out.println(item.getName());
        }
    }
}
