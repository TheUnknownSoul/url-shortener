package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
// https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~\#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_\+.~\#?&//=]*)  <<<<--- regex for url, endpoint on the second group

    @PostMapping("/index")
    String getLongURL(@RequestParam(value = "a", defaultValue = "b")String name){ // values should be filtering by regex
        return "";
    }
    @GetMapping("/index")
    String getShortURL(){

        return "";
    }
}
