package com.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Shortener implements ShortingService {
	private String URI;
	private static final int BASE = 62;

	public Shortener(String url){
		this.URI = url;
	}

	@Override
	public String encoder(int number) {
		StringBuilder sb = new StringBuilder();
		while (number>0){
			sb.append(URI.charAt(number%BASE));
			number/=BASE;
		}
		return sb.reverse().toString();
	}

	@Override
	public int decode(String str) {
		int num = 0;
		for (int i = 0; i <str.length() ; i++) {
			num = num * BASE + URI.indexOf(str.charAt(i));
		}
		return num;
	}
}
