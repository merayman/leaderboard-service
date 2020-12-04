package com.gjg.leaderboard;

import com.gjg.leaderboard.service.LeaderboardService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class LeaderboardServiceTestConfiguration {
    @Bean
    @Primary
    public LeaderboardService leaderboardServiceT() {
        return Mockito.mock(LeaderboardService.class);
    }
}