package com.example.demo.a1;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class DemoReader implements ItemReader {

    private int i;
    private int h;

    @Override
    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (h > 0) {
            i = 0;
            h = 0;
        }
        if (i < 5){
            return i++;
        }
        h++;
        return null;
    }
}
