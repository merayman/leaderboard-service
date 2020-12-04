package com.gjg.leaderboard.dto;

import java.util.List;

public class LeaderboardDTO {
    private List<UserDTO> leaderboard;

    public LeaderboardDTO(List<UserDTO> leaderboard) {
        this.leaderboard = leaderboard;
    }

    public List<UserDTO> getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(List<UserDTO> leaderboard) {
        this.leaderboard = leaderboard;
    }
}
