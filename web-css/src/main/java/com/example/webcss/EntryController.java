package com.example.webcss;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EntryController {

    @GetMapping("getJusho")
    @ResponseBody
    public List<Jusho> getJusho(@RequestParam(value="postCode") String postCode){
        System.out.println(postCode);
        List<Jusho> list = new ArrayList<>();
        list.add(new Jusho("123-4567", "東京都中央区１", "トウキョウトチュウオウク１"));
        list.add(new Jusho("123-4567", "東京都中央区２", "トウキョウトチュウオウク２"));
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
