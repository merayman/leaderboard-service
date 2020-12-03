package com.gjg.leaderboard.dto;

public class BaseUserDTO {

    private String name;
    private String countryCode;

    public BaseUserDTO(String name, String countryCode) {
        this.name = name;
        this.countryCode = countryCode;
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
