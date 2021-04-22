package com.common;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Class for describing long and short URL.
 * Has two param: short and long (full) url.
 */
@Entity
public class ShortenUrl {
    @Id
    private int id;

    private String long_url;

    private String short_url;


    public String getLong_url() {
        return long_url;
    }

    public void setLong_url(String long_url) {
        this.long_url = long_url;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

}
