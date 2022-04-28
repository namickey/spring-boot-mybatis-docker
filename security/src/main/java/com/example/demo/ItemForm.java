package com.example.demo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ItemForm {

    @NotEmpty
    private String id;
    private String name;

}
