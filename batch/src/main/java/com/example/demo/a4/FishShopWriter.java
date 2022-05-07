package com.example.demo.a4;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FishShopWriter implements ItemWriter<Item> {
    private int i;

    @Override
    public void write(List<? extends Item> list) throws Exception {
        for (Item item: list) {
            i++;
            if (i % 10000 == 1) {
                System.out.println(item.getName() + ", " + item.getCode());
            }
        }
    }
}
