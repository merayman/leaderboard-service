package com.gjg.leaderboard.repository;

import com.gjg.leaderboard.model.BaseUser;
import com.gjg.leaderboard.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class RedisRepositoryImpl implements RedisRepository{

    private static final String USERS_KEY = "users";
    private static final String LB_KEY = "leaderboard";

    private RedisTemplate<String, BaseUser> redisTemplate;
    private ZSetOperations<String, BaseUser> sortedSetOperations;
    private HashOperations<String, String, BaseUser> hashOperations;

    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, BaseUser> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        sortedSetOperations = redisTemplate.opsForZSet();
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public BaseUser addUser(BaseUser baseUser) {
        double initPoint = 0;
        String userId = baseUser.getId().toString();
        String countryCode = baseUser.getCountryCode();
        hashOperations.put(USERS_KEY,userId, baseUser);
        Boolean retVal = sortedSetOperations.add(LB_KEY, baseUser,initPoint);
        sortedSetOperations.add(LB_KEY+"-"+countryCode, baseUser,initPoint);
        if(retVal)
            return baseUser;
        else
            throw new RuntimeException("Redis can't add user whose user id:" + baseUser.getId());
    }

    @Override
    public List<User> getLeaderboard(long from, long size) {
        List<User> leaderboard = new ArrayList();
        sortedSetOperations.rangeWithScores(LB_KEY, from, from+size)
                .forEach(u -> leaderboard.
                        add(new User(u.getValue(),u.getScore(),
                                sortedSetOperations.reverseRank(LB_KEY,u.getValue()) + 1,
                                sortedSetOperations.reverseRank(LB_KEY+"-"+u.getValue().getCountryCode(),u.getValue())+1)));
        return leaderboard;
    }


    @Override
    public List<User> getCountryLeaderboard(String countryCode, long from, long size) {
        List<User> leaderboard = new ArrayList();
        sortedSetOperations.rangeWithScores(LB_KEY+"-"+countryCode, from, from+size)
                .forEach(u -> leaderboard.
                        add(new User(u.getValue(),u.getScore(),
                                sortedSetOperations.reverseRank(LB_KEY,u.getValue()) + 1,
                                sortedSetOperations.reverseRank(LB_KEY+"-"+u.getValue().getCountryCode(),u.getValue())+1)));
        return leaderboard;
    }

    @Override
    public BaseUser getUser(String id) {
            BaseUser baseUser = hashOperations.get(USERS_KEY,id);
            if (baseUser == null) {
                throw new RuntimeException("There is no user with user id:" + id);
            }
            return baseUser;
        }


    @Override
    public Double submitScore(BaseUser baseUser, double scoreWorth) {
        String countryCode = baseUser.getCountryCode();
        sortedSetOperations.incrementScore(LB_KEY+"-"+countryCode, baseUser,scoreWorth);
        return sortedSetOperations.incrementScore(LB_KEY, baseUser,scoreWorth);
    }

    @Override
    public Long getNumOfPlayers() {
        return sortedSetOperations.size(LB_KEY);
    }

    @Override
    public Long getNumOfPlayers(String countryCode) {
        return sortedSetOperations.size(LB_KEY+"-"+countryCode);
    }

    @Override
    public Long getCountryRank(BaseUser baseUser) {
        String countryCode = baseUser.getCountryCode();
        return sortedSetOperations.reverseRank(LB_KEY+"-"+countryCode, baseUser) + 1;
    }

    @Override
    public Double getPoints(BaseUser baseUser) {
        return sortedSetOperations.score(LB_KEY, baseUser);
    }

    @Override
    public Long getGlobalRank(BaseUser baseUser){
        return sortedSetOperations.reverseRank(LB_KEY, baseUser) + 1;
    }

    @Override
    public List<BaseUser> addBulkUser(List<BaseUser> baseUserList) {
        return null;
    }
        /*    double initPoint = 0;
        Map<String, BaseUser> baseUserMap = new HashMap();

        Set<ZSetOperations.TypedTuple<BaseUser>> sets = new HashSet(baseUserMap.values());
        for (ZSetOperations.TypedTuple<BaseUser> s: sets
             ) {
            s.getScore();
        }
        for (BaseUser baseUser : baseUserList
             ) {
            baseUserMap.put(baseUser.getId(),baseUser);
        }
        hashOperations.putAll(USERS_KEY,baseUserMap);
        sortedSetOperations.add(LB_KEY,sets);
        sortedSetOperations.add(LB_KEY,sets);
        return baseUserList;
    }
*/

}
