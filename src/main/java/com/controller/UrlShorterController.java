package com.controller;


import com.service.ShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
public class UrlShorterController {

    @Autowired
    ShortenerService shortenerService;

    @PostMapping(value = "/shortenurl")
    public String getShortenUrl(@RequestBody String longUrl) {
        String s = "";
              s = longUrl.replaceAll("[\"]+", "");

//        Pattern p = Pattern.compile("^[a-zA-Z ]*$");
//        Matcher m = p.matcher(longUrl);
        System.out.println(s);
//        if (m.matches()){
        return shortenerService.getShortenUrl(s);
//        }else {
//            return "";
//        }
    }

    @GetMapping(value = "/{shortUrl}")
    public ResponseEntity<Void> getLongUrl(@PathVariable("shortUrl") String shortUrl) {
        URI uri = shortenerService.getLongUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
    }
}