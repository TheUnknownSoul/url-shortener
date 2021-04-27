package com.model;


import lombok.*;

import javax.persistence.*;

/**
 * Class for describing long and short URL.
 * Has two param: short and long (full) url.
 */
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortenUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "long_url")
    private String longUrl;

    @Column(name = "short_url")
    private String shortUrl;
}
