package com.gjg.leaderboard.controller;

import com.gjg.leaderboard.dto.*;
import com.gjg.leaderboard.model.BaseUser;
import com.gjg.leaderboard.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class WebController {

    @Autowired
    private LeaderboardService leaderboardService;

    /*
    HTTP GET request: /leaderboard{countryCode}{from}{size}
    {countryCode}: optional, default: empty string
    {from}: optional, default: 0
    {size}: optional, default: 50
    It takes country code, from which rank, and size of part of data as parameters
    from and size params are used for pagination to scale the request
    If specified country code is given it returns the leaderboard of the country
    It returns leaderboard data as list of user infos:
    GUID, point, global rank, country rank, name country code
    */
    @RequestMapping(value = "/leaderboard", method = RequestMethod.GET)
    public ResponseEntity<LeaderboardDTO> leaderboard(
            @RequestParam(defaultValue = "" ,value = "countryCode", required = false)
            String countryCode,
            @RequestParam(defaultValue = "0",value = "from", required = false)
                    long from,
            @RequestParam(defaultValue = "50" ,value = "size", required = false)
            long size){
        return new ResponseEntity<>(leaderboardService
                .getLeaderboard(countryCode,from,size), HttpStatus.OK);
    }

    /*
    HTTP POST request: /score/submit
    It takes value of score worth and username in request body
    It submits score and returns submission info:
    score worth, user id, timestamp
    */
    @RequestMapping(value = "/score/submit", method = RequestMethod.POST)
    public ResponseEntity<ScoreDTO> submitScore(
            @RequestBody
            ScoreDTO scoreDTO) {
        UserDTO userDTO = leaderboardService.getUser(scoreDTO.getUserId());
        return new ResponseEntity<>(leaderboardService
                .submitScore(new BaseUser(
                userDTO.getId(),userDTO.getName(),userDTO.getCountryCode()),
                scoreDTO.getScoreWorth())
                ,HttpStatus.OK);
    }

    /*
    HTTP GET request: /user/profile{GUID}
    It takes GUID as parameter and returns user profile info:
    GUID, point, global rank, country rank, name country code
 */
    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> userProfile(
            @RequestParam(value = "id")
            String userId){
        return new ResponseEntity<>(leaderboardService.getUser(userId),HttpStatus.OK);
    }

    /*
    HTTP POST request: /user/create
    It takes value of username and country code in request body
    It creates user and returns created user info:
    GUID, point(initially 0), global rank(initially latest), country rank(initially latest), name country code
     */
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> createUser(
            @RequestBody
            UserDTO userDTO){
        return new ResponseEntity<>(leaderboardService
                .createUser(userDTO.getName(),userDTO.getCountryCode())
                ,HttpStatus.OK);
    }
}