package com.example.demo;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private String name;
    private String nameKeyless;
    private String nameNull;
    private String nameEmpty;

    private Integer age;
    private Integer ageKeyless;
    private Integer ageNull;
    private Integer ageEmpty;

    private Item item;
    private Item itemKeyless;
    private Item itemNull;
    private Item itemEmpty;

    private List<String> codeList;
    private List<String> codeListKeyless;
    private List<String> codeListNull;
    private List<String> codeListEmptyList;

    private List<Item> itemList;
    private List<Item> itemListKeyless;
    private List<Item> itemListNull;
    private List<Item> itemListEmptyList;

}
