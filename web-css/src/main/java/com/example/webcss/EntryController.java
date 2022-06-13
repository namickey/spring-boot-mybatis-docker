package com.example.webcss;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EntryController {

    @GetMapping("getJusho")
    @ResponseBody
    public Object getJusho(@RequestParam(value="postCode") String postCode){

        /*try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        if (postCode == null || "".equals(postCode)){
            return ResponseEntity.status(400).body("postCode must be required.");
        }
        List<Jusho> list = new ArrayList<>();
        if (postCode.length() == 7){
            if (Integer.valueOf(postCode.substring(6)) % 2 == 1){
                list.add(new Jusho("123-4567", "東京都中央区１", "トウキョウトチュウオウク１"));
                list.add(new Jusho("123-4567", "東京都中央区２", "トウキョウトチュウオウク２"));
            }else {
                list.add(new Jusho("987-6543", "埼玉県さいたま市１", "サイタマケンサイタマシ１"));
                list.add(new Jusho("987-6543", "埼玉県さいたま市２", "サイタマケンサイタマシ２"));
            }
        } else {
            list.add(new Jusho("123-4567", "東京都中央区１", "トウキョウトチュウオウク１"));
            list.add(new Jusho("123-4567", "東京都中央区２", "トウキョウトチュウオウク２"));
        }
        return list;
    }

    @GetMapping("index")
    public String index(){

        return "index";
    }

    @GetMapping("confirm")
    public String confirm(){

        return "confirm";
    }

    @GetMapping("entry")
    public String entry(){

        return "complete";
    }
}
