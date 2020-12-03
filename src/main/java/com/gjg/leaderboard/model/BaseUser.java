package com.gjg.leaderboard.model;

import java.io.Serializable;

public class BaseUser implements Serializable {

    private String id;
    private String name;
    private String countryCode;

    public BaseUser(String id, String name, String countryCode){
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}