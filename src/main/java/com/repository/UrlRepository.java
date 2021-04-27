package com.repository;

import com.model.ShortenUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UrlRepository extends JpaRepository<ShortenUrl, Integer> {

    Optional<ShortenUrl> findByLongUrl(String longUrl);
}
