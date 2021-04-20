package com.controller;
import java.io.IOException;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.common.ShortenUrl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShorterRestController {

    private final  Map<String, ShortenUrl> shortenUrlList = new HashMap<>();

    @RequestMapping(value="/shortenurl", method=RequestMethod.POST)
    public ResponseEntity<Object> getShortenUrl(@RequestBody ShortenUrl shortenUrl){
        String randomChar = getRandomChars();
        setShortUrl(randomChar, shortenUrl);
        return new ResponseEntity<>(shortenUrl, HttpStatus.OK);
    }

    @RequestMapping(value="/s/{randomstring}", method=RequestMethod.GET)
    public void getFullUrl(HttpServletResponse response, @PathVariable("randomstring") String randomString) throws IOException {
        response.sendRedirect(shortenUrlList.get(randomString).getFull_url());
    }

    private void setShortUrl(String randomChar, ShortenUrl shortenUrl) {
        shortenUrl.setShort_url("http://localhost:8080/s/"+randomChar);
        shortenUrlList.put(randomChar, shortenUrl);
    }

    private String getRandomChars() {
        StringBuilder randomStr = new StringBuilder();
        String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 5; i++)
            randomStr.append(possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length())));
        return randomStr.toString();
    }

}