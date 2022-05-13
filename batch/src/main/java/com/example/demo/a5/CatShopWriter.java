package com.example.demo.a5;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatShopWriter implements ItemWriter<Item> {

    @Override
    public void write(List<? extends Item> list) throws Exception {
        for (Item item: list) {
            System.out.println("1 " + item.getName() + ", " + item.getCode());
        }
    }
}
