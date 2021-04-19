package com.service;

import org.springframework.stereotype.Service;

@Service
public interface ShortingService {
	 String encoder(int number);

	 int decode(String str);
}
