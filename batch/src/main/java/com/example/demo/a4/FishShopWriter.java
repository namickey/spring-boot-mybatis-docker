package com.example.demo.a4;

import com.example.demo.a3.Item;
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
            if (i % 1000 == 1) {
                System.out.println(item.getName());
            }
        }
    }
}
