package com.gjg.leaderboard.Repository;

import com.gjg.leaderboard.Model.BaseUser;
import com.gjg.leaderboard.Model.User;


import java.util.List;
import java.util.UUID;

public interface RedisRepository {


    //gets global leaderboard
    List<User> getLeaderboard(long from, long size);

    //gets country leaderboard by country code
    List<User> getCountryLeaderboard(String countryCode, long from, long size);

    //adds a baseUser and returns the added baseUser
    BaseUser addUser(BaseUser baseUser);

    //gets the user by id
    BaseUser getUser(UUID id);

    //submits a score and returns the respective baseUser
    Double submitScore(BaseUser baseUser, double scoreWorth);

    //returns total number of players globally
    Long getNumOfPlayers();

    //returns total number of players in that country
    Long getNumOfPlayers(String countryCode);

    //returns global rank by baseUser
    Long getGlobalRank(BaseUser baseUser);

    //returns global rank by baseUser
    Long getCountryRank(BaseUser baseUser);

    //
    Double getPoints(BaseUser baseUser);
}