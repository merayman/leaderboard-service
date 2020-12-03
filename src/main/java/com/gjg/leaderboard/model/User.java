package com.gjg.leaderboard.model;

public class User extends BaseUser {
    private Double points;
    private Long globalRank;
    private Long countryRank;

    public User(BaseUser baseUser, Double points, Long globalRank, Long countryRank ) {
        super(baseUser.getId(), baseUser.getName(), baseUser.getCountryCode());
        this.points = points;
        this.globalRank = globalRank;
        this.countryRank = countryRank;
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

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }
}
