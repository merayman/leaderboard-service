package com.gjg.leaderboard.dto;

public class ScoreDTO {
    private double scoreWorth;
    private String userId;
    private long timestamp;

    public ScoreDTO(double scoreWorth, String userId, long timestamp) {
        this.scoreWorth = scoreWorth;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public double getScoreWorth() {
        return scoreWorth;
    }

    public void setScoreWorth(double scoreWorth) {
        this.scoreWorth = scoreWorth;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
