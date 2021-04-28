package com;

import com.service.ShortenerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ShortenerServiceTest {

    @Autowired
    ShortenerService shortenerService;


    @Test
    public void getShortUrlTest() {
        String longUrl = "https://github.com/";
        String actualResult = shortenerService.getShortenUrl(longUrl);
        String expectedResult = "http://localhost:8080/B";
        Assertions.assertEquals(actualResult, expectedResult);

    }


}