package com.gjg.leaderboard.controller;

import com.gjg.leaderboard.dto.*;
import com.gjg.leaderboard.model.BaseUser;
import com.gjg.leaderboard.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/")
public class WebController {

    @Autowired
    private LeaderboardService leaderboardService;
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

    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> userProfile(
            @RequestParam(value = "id")
            String userId){
        return new ResponseEntity<>(leaderboardService.getUser(userId),HttpStatus.OK);
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> createUser(
            @RequestBody
            UserDTO userDTO){
        return new ResponseEntity<>(leaderboardService
                .createUser(userDTO.getName(),userDTO.getCountryCode())
                ,HttpStatus.OK);
    }
/*
    @RequestMapping(value = "/user/createBulk", method = RequestMethod.POST)
    public ResponseEntity<BulkResponseDTO> createUserBulk(
            @RequestBody
                    List<UserDTO> userDTOList){

        return new ResponseEntity<>(new BulkResponseDTO(leaderboardService
                .createBulkUser(new HashMap<String, String>(
                        userDTOList.forEach(userDTO -> );)).size())
                ,HttpStatus.OK);
    }
*/
    /*

    @RequestMapping(value = "/score/submitBulk", method = RequestMethod.POST)
    public ResponseEntity<BulkResponseDTO> submitScore(
            @RequestBody
                    List<ScoreDTO> scoreDTOList) {
        return new ResponseEntity<>(leaderboardService
                .submitBulkScore(scoreDTOList), HttpStatus.OK);
    }
      */


}