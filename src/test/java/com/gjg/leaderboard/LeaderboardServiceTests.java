package com.gjg.leaderboard;


import com.gjg.leaderboard.dto.LeaderboardDTO;
import com.gjg.leaderboard.dto.ScoreDTO;
import com.gjg.leaderboard.dto.UserDTO;
import com.gjg.leaderboard.model.BaseUser;
import com.gjg.leaderboard.repository.RedisRepository;
import com.gjg.leaderboard.service.LeaderboardService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class LeaderboardServiceTests {

    @Autowired
    private LeaderboardService leaderboardService;

    @Mock
    private RedisRepository redisRepository;

    //getUserTest
    @Test
    public void whenUserIdIsGiven_thenRetrievedUserIsCorrect(){
        UserDTO userDTO = new UserDTO("",3.0,1L,1L,"musab","tr");
        Mockito.when(leaderboardService.getUser("")).thenReturn(userDTO);
        UserDTO test = leaderboardService.getUser("");
        Assert.assertEquals(userDTO, test);
    }

    //getLeaderboard by country test
    @Test
    public void whenCountryIsGiven_thenRetrieveGlobalLeaderboard(){
        List<UserDTO> userDTOList = new ArrayList();
        LeaderboardDTO leaderboardDTO = new LeaderboardDTO(userDTOList);
        Mockito.when(leaderboardService.getLeaderboard("tr",0,50)).thenReturn(leaderboardDTO);
        LeaderboardDTO testDTO = leaderboardService.getLeaderboard("tr",0,50);
        Assert.assertEquals(leaderboardDTO,testDTO);
    }

    //getLeaderboard test
    @Test
    public void whenNoCountryIsGiven_thenRetrieveGlobalLeaderboard(){
        List<UserDTO> userDTOList = new ArrayList();
        LeaderboardDTO leaderboardDTO = new LeaderboardDTO(userDTOList);
        Mockito.when(leaderboardService.getLeaderboard("",0,50)).thenReturn(leaderboardDTO);
        LeaderboardDTO testDTO = leaderboardService.getLeaderboard("",0,50);
        Assert.assertEquals(leaderboardDTO,testDTO);
    }



    //score submission test
    @Test
    public void submitScoreTest(){
        long timestamp = System.currentTimeMillis() / 1000;
        ScoreDTO scoreDTO = new ScoreDTO(10.0,"",timestamp);
        BaseUser baseUser = new BaseUser("", "musab","tr");
        Mockito.when(leaderboardService.submitScore(baseUser,10.0)).thenReturn(scoreDTO);
        Assert.assertEquals(scoreDTO,leaderboardService.submitScore(baseUser,10.0));
    }

    //createUser test
    @Test
    public void createUserTest(){
        UserDTO userDTO = new UserDTO("id", 3.0,new Long(1),new Long(1),"name","tr");
        Mockito.when(leaderboardService.createUser("name","tr")).thenReturn(userDTO);
        Assert.assertEquals(userDTO,leaderboardService.createUser("name","tr"));
    }


}
