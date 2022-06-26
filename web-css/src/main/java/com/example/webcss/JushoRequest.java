package com.example.webcss;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class JushoRequest {

    @Size(min = 3, max = 3)
    private String zipCodeFront;

    @Size(min = 4, max = 4)
    private String zipCodeBack;
}
