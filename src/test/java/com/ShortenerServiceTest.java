package com;

import static org.assertj.core.api.Assertions.assertThat;


import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import com.controller.UrlShorterController;
import com.model.ShortenUrl;
import com.repository.UrlRepository;
import com.service.ShortenerService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ShortenerServiceTest {

    @InjectMocks
    ShortenerService shortenerService = new ShortenerService();

    @Mock
    UrlRepository repository;

    @Before("")
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getShortUrlTest() {
        String longUrl = "https://github.com/";
        ShortenUrl shortenUrl = ShortenUrl.builder()
                .id(1)
                .longUrl(longUrl)
                .build();
        when(repository.findByLongUrl(longUrl)).thenReturn(Optional.empty());
        when(repository.save(shortenUrl)).thenReturn(shortenUrl);

        String result = shortenerService.getShortenUrl(longUrl);

        Assertions.assertEquals("http://localhost:8080/B", result);

    }
}
