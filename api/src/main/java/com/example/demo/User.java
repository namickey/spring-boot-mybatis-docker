package com.example.demo;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String id;
    private String name;
    private List<String> code;
}
