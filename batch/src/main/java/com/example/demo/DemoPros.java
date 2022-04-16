package com.example.demo;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class DemoPros implements ItemProcessor<Integer, String> {

    @Override
    public String process(Integer integer) throws Exception {
        return integer.toString();
    }
}
