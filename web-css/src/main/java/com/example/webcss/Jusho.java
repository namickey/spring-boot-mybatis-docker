package com.example.webcss;

import lombok.Data;

@Data
public class Jusho {
    private String postCode;
    private String jushoKanji;
    private String jushoKana;

    public Jusho(String postCode, String jushoKanji, String jushoKana){
        this.postCode = postCode;
        this.jushoKanji = jushoKanji;
        this.jushoKana = jushoKana;
    }
}
