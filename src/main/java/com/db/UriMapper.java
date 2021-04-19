package com.db;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class UriMapper {

	private String shortURL;
	private String longURL;

	@Id
	private Long id;



}
	// public static
