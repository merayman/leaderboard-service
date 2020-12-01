package com.gjg.leaderboard.Model;

import java.io.Serializable;
import java.util.UUID;


public class BaseUser implements Serializable {

    private UUID id;
    private String name;
    private String countryCode;


    public BaseUser(UUID id, String name, String countryCode){
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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