package com.example.webcss;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JushoController {

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
}
