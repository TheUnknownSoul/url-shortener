package com;

import com.model.ShortenUrl;
import com.repository.UrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UrlRepositoryTest {

    @MockBean
    private UrlRepository mockRepository;

    @Test
    public void getEntityFromJPARepository() {
        ShortenUrl url = new ShortenUrl();
        when(mockRepository.findById(1)).thenReturn(Optional.of(url));
    }
}
