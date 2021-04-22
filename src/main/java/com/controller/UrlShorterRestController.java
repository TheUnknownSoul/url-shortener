package com.controller;

import java.io.IOException;


import java.sql.*;
//import java.util.HashMap;
//import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.common.ShortenUrl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShorterRestController {
    private static final Logger logger = LogManager.getLogger(UrlShorterRestController.class);


    @RequestMapping(value = "/shortenurl", method = RequestMethod.POST)
    public ResponseEntity<Object> getShortenUrl(@RequestBody ShortenUrl shortenUrl) {
        try (Connection con = DriverManager.getConnection("jdbc:h2:file:~/urls;DB_CLOSE_DELAY=-1", "root", "root")) {

            Statement statement = con.createStatement();
            try (ResultSet rs = statement.executeQuery("SELECT * FROM  URLS;")) {
                while (rs.next()) {
                    String tempLong = shortenUrl.getLong_url();
                    if (tempLong.equals(rs.getString("long_url"))) {
                        return new ResponseEntity<>(rs.getString("long_url"), HttpStatus.OK);
                    }
                }
            }
            String randomChar = getRandomChars();
            setShort_Url(randomChar, shortenUrl);

        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }
        return new ResponseEntity<>(shortenUrl, HttpStatus.OK);
    }

    @RequestMapping(value = "/s/{randomstring}", method = RequestMethod.GET)
    public void getLong_Url(HttpServletResponse response, @PathVariable("randomstring") String randomString) {
        try (Connection con = DriverManager.getConnection("jdbc:h2:file:~/urls;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false", "root", "root")) {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM  URLS");
            while (resultSet.next()) {
                String temp = resultSet.getString("short_url");
                temp = temp.trim();
                String k = "http://localhost:8080/s/" + randomString;
                if (temp.equals(k)) {
                    String longUrl = resultSet.getString("long_url");
                    longUrl = longUrl.trim();

                    response.sendRedirect(longUrl);
                }
            }

        } catch (SQLException | IOException e) {
            logger.error("Exception in: " + e.getMessage());
        }
    }

    private void setShort_Url(String randomChar, ShortenUrl shortenUrl) {
        try (Connection con = DriverManager.getConnection("jdbc:h2:file:~/urls;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false", "root", "root")) {
            shortenUrl.setShort_url("http://localhost:8080/s/" + randomChar);
            Statement statement = con.createStatement();
            statement.execute("INSERT INTO URLS VALUES (default, ' " + shortenUrl.getShort_url() + "', '" + shortenUrl.getLong_url() + "' );");
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    private String getRandomChars() {
        StringBuilder randomStr = new StringBuilder();
        String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 5; i++) {
            randomStr.append(possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length())));
        }
        return randomStr.toString();
    }

}