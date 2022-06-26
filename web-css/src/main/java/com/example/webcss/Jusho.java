package com.example.webcss;

import lombok.Data;

@Data
public class Jusho {
    private String zipCode;
    private String jushoKanji;
    private String jushoKana;

    public Jusho(String zipCode, String jushoKanji, String jushoKana){
        this.zipCode = zipCode;
        this.jushoKanji = jushoKanji;
        this.jushoKana = jushoKana;
    }
}
