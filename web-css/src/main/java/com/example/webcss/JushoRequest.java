package com.example.webcss;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class JushoRequest {
    @NotEmpty
    private String postCode;
}
