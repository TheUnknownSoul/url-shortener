package com.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ServiceConfig {

    public String LOCALHOST_URL;

    @Autowired
    public ServiceConfig(@Value("${application.LOCALHOST_URL}") String LOCALHOST_URL) {
        this.LOCALHOST_URL = LOCALHOST_URL;
    }
}

