package com.example.demo.a2;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ShopPros implements ItemProcessor<Item, Item> {
    @Override
    public Item process(Item item) throws Exception {
        Item newItem = new Item();
        newItem.setId(item.getId());
        newItem.setName(item.getName() + " ✕ " + item.getName());
        return newItem;
    }
}
