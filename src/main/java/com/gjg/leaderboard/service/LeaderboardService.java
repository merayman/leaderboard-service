package com.gjg.leaderboard.service;

import com.gjg.leaderboard.dto.*;
import com.gjg.leaderboard.model.BaseUser;
import com.gjg.leaderboard.model.User;
import com.gjg.leaderboard.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Collections.reverse(leaderBoard);
        return new LeaderboardDTO(leaderBoard);
    }

    public UserDTO getUser(String userId){
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
        String id = UUID.randomUUID().toString();
        BaseUser created = new BaseUser(id,name,countryCode);
        BaseUser baseUser = redisRepository.addUser(created);
        Long globalRank = redisRepository.getGlobalRank(baseUser);
        Long countryRank = redisRepository.getCountryRank(baseUser);
        Double points = redisRepository.getPoints(baseUser);
        UserDTO userDTO = new UserDTO(id,points,globalRank,countryRank,name,countryCode);
        return userDTO;
    }

    public BulkResponseDTO createBulkUser(ArrayList<BaseUserDTO> baseUserDTOArrayList)
    { return null;}
        /*
        List<BaseUser> baseUsers = new ArrayList();
        for (String name : nameCountryMap.keySet()
             ) {
            String id = UUID.randomUUID().toString();
            BaseUser created = new BaseUser(id,name,nameCountryMap.get(name));
            baseUsers.add(created);
        }
        List<BaseUser> addedBaseUsers = redisRepository.addBulkUser(baseUsers);
        return new BulkResponseDTO(addedBaseUsers.size());

    }
         */

    public ScoreDTO submitScore(BaseUser baseUser, double scoreWorth){
        long timestamp = System.currentTimeMillis() / 1000;
        Double updatedScore = redisRepository.submitScore(baseUser,scoreWorth);
        String id = baseUser.getId();
        ScoreDTO scoreDTO = new ScoreDTO(scoreWorth,id,timestamp);
        return scoreDTO;
    }

}
