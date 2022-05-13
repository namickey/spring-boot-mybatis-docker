package com.example.demo.a3;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FoodShopPros implements ItemProcessor<Item, Item> {
    @Override
    public Item process(Item item) throws Exception {
        Item newItem = new Item();
        newItem.setId(item.getId());
        newItem.setName(item.getName());
        newItem.setCode(item.getCode());
        return newItem;
    }
}
