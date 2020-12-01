package com.gjg.leaderboard.Service;

import com.gjg.leaderboard.DTO.LeaderboardDTO;
import com.gjg.leaderboard.DTO.ScoreDTO;
import com.gjg.leaderboard.DTO.UserDTO;
import com.gjg.leaderboard.Model.BaseUser;
import com.gjg.leaderboard.Repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LeaderboardService {


    @Autowired
    private RedisRepository redisRepository;

    public LeaderboardDTO getLeaderboard(String countryCode, long from, long size){
        List<UserDTO> leaderBoard = new ArrayList();
        if (countryCode.equals("")) {
            redisRepository.getLeaderboard(from, size).
                    forEach(u -> leaderBoard.add(
                            new UserDTO(u.getId(),u.getPoints(),
                                u.getGlobalRank(),u.getCountryRank(),
                                u.getName(),u.getCountryCode())));
        }
        else{
            redisRepository.getCountryLeaderboard(countryCode, from, size).
                    forEach(u -> leaderBoard.add(
                            new UserDTO(u.getId(),u.getPoints(),
                                    u.getGlobalRank(),u.getCountryRank(),
                                    u.getName(),u.getCountryCode())));
        }
            return new LeaderboardDTO(leaderBoard);
    }

    public UserDTO getUser(UUID userId){
        BaseUser baseUser = redisRepository.getUser(userId);
        String userName = baseUser.getName();
        String countryCode = baseUser.getCountryCode();
        Long globalRank = redisRepository.getGlobalRank(baseUser);
        Long countryRank = redisRepository.getCountryRank(baseUser);
        Double points = redisRepository.getPoints(baseUser);
        UserDTO userDTO = new UserDTO(userId,points,globalRank,countryRank,userName,countryCode);
        return userDTO;
    }

    public UserDTO createUser(String name, String countryCode){
        UUID id = UUID.randomUUID();
        BaseUser created = new BaseUser(id,name,countryCode);
        BaseUser baseUser = redisRepository.addUser(created);
        Long globalRank = redisRepository.getGlobalRank(baseUser);
        Long countryRank = redisRepository.getCountryRank(baseUser);
        Double points = redisRepository.getPoints(baseUser);
        UserDTO userDTO = new UserDTO(id,points,globalRank,countryRank,name,countryCode);
        return userDTO;
    }

    public ScoreDTO submitScore(BaseUser baseUser, double scoreWorth){
        long timestamp = System.currentTimeMillis() / 1000;
        Double updatedScore = redisRepository.submitScore(baseUser,scoreWorth);
        UUID id = baseUser.getId();
        ScoreDTO scoreDTO = new ScoreDTO(scoreWorth,id,timestamp);
        return scoreDTO;
    }

}
