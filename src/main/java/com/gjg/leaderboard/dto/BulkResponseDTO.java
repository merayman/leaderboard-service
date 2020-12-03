package com.gjg.leaderboard.dto;

public class BulkResponseDTO {
    private int bulkCount;


    public int getBulkCount() {
        return bulkCount;
    }

    public void setBulkCount(int bulkCount) {
        this.bulkCount = bulkCount;
    }

    public BulkResponseDTO(int bulkCount) {
        this.bulkCount = bulkCount;

    }
}
