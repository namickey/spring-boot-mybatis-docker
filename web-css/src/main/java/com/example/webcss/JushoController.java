package com.example.webcss;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class JushoController {

    @GetMapping("getJusho")
    @ResponseBody
    public Object getJusho(@Valid JushoRequest req){

        if ("a".equals(req.getPostCode())){
            return ResponseEntity.status(400).body(Map.of("errorMessage","no data"));
        }

        List<Jusho> jushoList = new ArrayList<>();
        jushoList.add(new Jusho("123-4567", "東京都中央区１", "トウキョウトチュウオウク１"));
        jushoList.add(new Jusho("123-4567", "東京都中央区２", "トウキョウトチュウオウク２"));
        JushoResponse res = new JushoResponse();
        res.setJushoList(jushoList);
        return res;
    }
}
