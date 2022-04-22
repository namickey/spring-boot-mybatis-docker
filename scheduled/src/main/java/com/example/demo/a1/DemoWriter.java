package com.example.demo.a1;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DemoWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) throws Exception {
        //Thread.sleep(3000);
        System.out.println(list);
    }
}
