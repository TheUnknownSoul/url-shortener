package com.service;


import com.config.ServiceConfig;
import com.model.ShortenUrl;
import com.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ShortenerService {

    private static final String POSSIBLE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Autowired
    UrlRepository repository;

    @Autowired
    ServiceConfig serviceConfig;

    public String getShortenUrl(String longUrl) {
        Optional<ShortenUrl> optionalShortenUrl = repository.findByLongUrl(longUrl);
        if (!optionalShortenUrl.isPresent()) {
            ShortenUrl shortenUrl = ShortenUrl.builder()
                    .longUrl(longUrl)
                    .build();
            shortenUrl = repository.save(shortenUrl);
            return serviceConfig.LOCALHOST_URL + createShortUrl(shortenUrl.getId());
        } else {
            return serviceConfig.LOCALHOST_URL + createShortUrl(optionalShortenUrl.get().getId());
        }
    }

    public URI getLongUrl(String shortUrl) {
        Integer id = decodeId(shortUrl);
        Optional<ShortenUrl> optionalShortenUrl = repository.findById(id);
        if (optionalShortenUrl.isPresent()) {
            return URI.create(optionalShortenUrl.get().getLongUrl());
        } else {
            throw new NoSuchElementException("No such url in db");
        }
    }

    private Integer decodeId(String shortUrl) {
        int id = 0;
        if (shortUrl != null && !shortUrl.equals("")) {
            for (int i = 0; i < shortUrl.length(); i++) {
                char c = shortUrl.charAt(i);
                id += POSSIBLE_CHARS.indexOf(c);
            }
        } else {
            throw new IllegalArgumentException("illegal argument");
        }
        return id;

    }

    private String createShortUrl(Integer id) {
        StringBuilder result = new StringBuilder();
        do {
            id = id % 66;
            result.append(POSSIBLE_CHARS.charAt(id));
        } while (id >= POSSIBLE_CHARS.length());
        return result.toString();
    }
}
