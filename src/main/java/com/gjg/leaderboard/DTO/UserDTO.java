package com.gjg.leaderboard.DTO;

import java.util.UUID;

public class UserDTO {
    private UUID id;
    private Double points;
    private Long globalRank;
    private Long countryRank;
    private String name;
    private String countryCode;

    public UserDTO(UUID id, Double points, Long globalRank, Long countryRank, String name, String countryCode) {
        this.id = id;
        this.points = points;
        this.globalRank = globalRank;
        this.countryRank = countryRank;
        this.name = name;
        this.countryCode = countryCode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Long getGlobalRank() {
        return globalRank;
    }

    public void setGlobalRank(Long globalRank) {
        this.globalRank = globalRank;
    }

    public Long getCountryRank() {
        return countryRank;
    }

    public void setCountryRank(Long countryRank) {
        this.countryRank = countryRank;
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
