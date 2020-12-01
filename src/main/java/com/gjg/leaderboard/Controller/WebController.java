package com.gjg.leaderboard.Controller;

import com.gjg.leaderboard.DTO.LeaderboardDTO;
import com.gjg.leaderboard.DTO.ScoreDTO;
import com.gjg.leaderboard.DTO.UserDTO;
import com.gjg.leaderboard.Model.BaseUser;
import com.gjg.leaderboard.Service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.util.UUID;

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
            UUID userId){
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
}
