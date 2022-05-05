package com.example.demo.a4;

import com.example.demo.a3.Item;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FishShopPros implements ItemProcessor<Item, Item> {
    @Override
    public Item process(Item item) throws Exception {
        Item newItem = new Item();
        newItem.setId(item.getId());
        newItem.setName(item.getName());
        return newItem;
    }
}
