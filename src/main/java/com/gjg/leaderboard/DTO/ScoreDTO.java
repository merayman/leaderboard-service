package com.gjg.leaderboard.DTO;

import java.util.UUID;

public class ScoreDTO {
    private double scoreWorth;
    private UUID userId;
    private long timestamp;

    public ScoreDTO(double scoreWorth, UUID userId, long timestamp) {
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
