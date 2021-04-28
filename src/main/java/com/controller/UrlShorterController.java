package com.controller;

import com.model.ShortenUrl;
import com.service.ShortenerService;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlShorterController {
//    private static final Logger logger = LogManager.getLogger(UrlShorterController.class);
    @Autowired
    ShortenerService shortenerService;

    @PostMapping(value = "/shortenurl")
    public String getShortenUrl(@RequestBody String longUrl) {
        return shortenerService.getShortenUrl(longUrl);
    }

    @GetMapping(value = "/{shortUrl}")
    public ResponseEntity<Void> getLongUrl(@PathVariable("shortUrl") String shortUrl) {
        URI uri = shortenerService.getLongUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
    }
}